/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.Server;

import com.amazonaws.services.elasticmapreduce.model.Credentials;
import com.amazonaws.services.elasticmapreduce.model.UsernamePassword;

import java.io.IOException;
import java.io.InputStream;

import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricar
 */
public class AuthenticationThread implements Runnable {

    //change this to a boolean in the json demo
    private final int SUCESSMESSAGE = 0;
    private final int WRONGCREDENTIALS = -1;

    private final SocketChannel clientConnection;
    private InetSocketAddress address = null;

    private InetSocketAddress recieveAddress = null;

    public AuthenticationThread(SocketChannel newConnection) {
        clientConnection = newConnection;
        address = (InetSocketAddress) clientConnection.socket().getLocalSocketAddress();
        recieveAddress = new InetSocketAddress(Server.SERVER_RECIEVER_PORT);

    }

    @Override
    public void run() {
        UsernamePassword sessionCredentials = null;
        try (clientConnection; ServerSocketChannel recieveServer = ServerSocketChannel.open()) {

            try {
                recieveServer.bind(recieveAddress);
            } catch (BindException b) {
                Thread.sleep(100);
                recieveServer.bind(recieveAddress);
            }

            //lets just use json here
            sessionCredentials = retrieveSessionCredentials().getUsernamePassword();

            //data checks to the server
            int validCredentialFlag = true ? SUCESSMESSAGE : WRONGCREDENTIALS;

            //sends status
            try (SocketChannel outputHandle = recieveServer.accept()) {
                sendMessageBack(outputHandle, validCredentialFlag);
            }

        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuthenticationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Credentials retrieveSessionCredentials() {
        try (InputStream in = clientConnection.socket().getInputStream()) {
            System.out.println(address.getHostName() + " connected!");

            ObjectInputStream is = new ObjectInputStream(in);

            Credentials tmp = (Credentials) is.readObject();

            //autheticate with server if credentials are okay
            System.out.println(String.format("Received Session Credentials:\nUsername:%s\nPassword:%s\n", tmp.getUsernamePassword().getUsername(), tmp.getUsernamePassword().getPassword()));
            return tmp;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    private void sendMessageBack(SocketChannel clientConnection, int status) {
        try (OutputStream out = clientConnection.socket().getOutputStream()) {
            out.write(status);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
