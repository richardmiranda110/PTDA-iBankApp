package com.ua.ptda_ibankapp.JSONTest;

import com.ua.ptda_ibankapp.JSONTest.SocketMessageContainer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.StringJoiner;

public class Client {

    SocketChannel socketChannel = null;

    private SocketChannel createSocketChannel() throws IOException {
        socketChannel = SocketChannel.open();
        SocketAddress sAddr = new InetSocketAddress("localhost", 9100);
        socketChannel.connect(sAddr);
        return socketChannel;
    }

    private void sendMessageJSON(SocketMessageContainer messageContainer) throws IOException {
        socketChannel = createSocketChannel();

        //add limiter
        StringJoiner strJoiner = new StringJoiner("\f");

        strJoiner.add(messageContainer.getHead());
        strJoiner.add(messageContainer.getBody());

        //send it
        ByteBuffer buffer = ByteBuffer.wrap(strJoiner.toString().getBytes());
        socketChannel.write(buffer);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();

        //create gson builder
        GsonBuilder builder = new GsonBuilder();
        //builder.setPrettyPrinting();
        Gson gson = builder.create();

        //set socket head, we need to rewrite this class later
        SocketHead socketHead = new SocketHead();
        socketHead.setAction("login");

        //create messageContainer
        SocketMessageContainer messageContainer = new SocketMessageContainer();

        //define message properties
        messageContainer.setHead(gson.toJson(socketHead));
        messageContainer.setBody(gson.toJson(new JSONLoginTemplate("email", "password")));

        client.sendMessageJSON(messageContainer);
    }

}
