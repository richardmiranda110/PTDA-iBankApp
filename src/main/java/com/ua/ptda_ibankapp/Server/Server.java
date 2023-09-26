package com.ua.ptda_ibankapp.Server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author ricar
 */
public class Server {

    public static int SERVER_PORT = 9100;
    public static int SERVER_RECIEVER_PORT = SERVER_PORT + 10;

    public static void main(String[] args) {
        ServerSocketChannel serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            serverSocket = ServerSocketChannel.open();

            serverSocket.bind(new InetSocketAddress(SERVER_PORT));
            System.out.println(String.format("Server Started on port %d, listening on port %d!\nStill a WIP project, you may start your LoginScreen now", SERVER_PORT, SERVER_RECIEVER_PORT));

            while (serverSocket.isOpen()) {
                executorService.execute(new AuthenticationThread(serverSocket.accept()));
            }

        } catch (IOException e) {

        }
    }
}
