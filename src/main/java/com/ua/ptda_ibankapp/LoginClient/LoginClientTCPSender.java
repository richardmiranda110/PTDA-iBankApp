/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.LoginClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 *
 * @author ricar
 */
public class LoginClientTCPSender {

    private SocketChannel socketChannel = null;
    private InetSocketAddress remote = null;
    private InputStream in = null;

    public LoginClientTCPSender() {
        this.remote = new InetSocketAddress(ServerInfo.SERVER_ADDRESS, ServerInfo.SERVER_PORT);
    }

    private void prepareForComunication() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(remote);
            in = socketChannel.socket().getInputStream();

            while (!isBound()) {
                //wait
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public boolean isBound() {
        return socketChannel.socket().isBound() && socketChannel != null;
    }

    public void createAndSendLoginRequest(Object obj) {

        try (ObjectOutputStream os = new ObjectOutputStream(socketChannel.socket().getOutputStream())) {
            os.writeObject(obj);
        } catch (SocketException e) {
            //ignore since the ConnectException causes the Socket to stay null
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void createAndSendObject(Object obj) {

        try (ObjectOutputStream os = new ObjectOutputStream(socketChannel.socket().getOutputStream())) {
            os.writeObject(obj);
        } catch (SocketException e) {
            //ignore since the ConnectException causes the Socket to stay null
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void createSocketChannel() throws IOException, ConnectException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(remote);

    }

    public void closeSocketChannel() throws IOException {
        socketChannel.close();
    }
}
