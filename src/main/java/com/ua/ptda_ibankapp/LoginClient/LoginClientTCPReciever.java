/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.LoginClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.io.InputStream;
import java.net.ConnectException;

/**
 *
 * @author ricar
 */
public class LoginClientTCPReciever {

    private SocketChannel socketChannel = null;
    private InetSocketAddress remote = null;

    public LoginClientTCPReciever() {
        this.remote = new InetSocketAddress(ServerInfo.SERVER_ADDRESS, ServerInfo.SERVER_RECIEVER_PORT);
    }

    private boolean prepareForComunication() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(remote);

            while (!isBound()) {
                //wait
            }

            return true;
        } catch (IOException ex) {
            //ex.printStackTrace(System.out);
            return false;
        }
    }

    public boolean isBound() {
        return socketChannel.socket().isBound() && socketChannel != null;
    }

    Object recieveObject() {
        try (ObjectInputStream is = new ObjectInputStream(socketChannel.socket().getInputStream())) {
            prepareForComunication();

            Object answer = is.readObject();

            socketChannel.close();
            return answer;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public int recieveIncomingInteger() {

        prepareForComunication();

        try (InputStream in = socketChannel.socket().getInputStream()) {
            byte[] answer = new byte[1];
            in.read(answer);

            socketChannel.close();
            return (int) answer[0];

        } catch (IOException ex) {

            //ex.printStackTrace(System.out);
        }
        return -1;
    }
}
