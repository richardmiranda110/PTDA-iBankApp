/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ua.ptda_ibankapp.JSONTest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    SocketChannel socketChannel = null;
    private final String CHAR_LIMITER = "\f";

    private SocketChannel createSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9100));
        //eu tambem fiquei confuso com esta função, eu abro o server, espero receber algo e fecho quando receber cago no server
        socketChannel = serverSocketChannel.accept();
        return socketChannel;
    }

    private SocketMessageContainer recieveIncomingMessage() throws IOException {
        //spagheti but works ig
        socketChannel = createSocketChannel();

        //create nio recievedPacket
        byte[] recievedPacket = new byte[1024];
        ByteBuffer byteBuffer = ByteBuffer.wrap(recievedPacket);

        //write to the recievedPacket
        if ((socketChannel.read(byteBuffer)) == 0) {
            try {
                throw new Exception("NO DATA RECIEVED");
            } catch (Exception ex) {
                return null;
            }
        }

        String[] structure = new String(recievedPacket).split(CHAR_LIMITER);

        return new SocketMessageContainer(structure[0], structure[1]);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();

        SocketMessageContainer incomingSocketContent = server.recieveIncomingMessage();

        // ESTA MERDA CHAMOU O MEU JSON DE MALFORMED, PO CARALHO D:
        //gonçalo ou richard pls, eu estou a ficar sem sanidade mental
        System.out.println(String.format("HEAD:\n%s\nBODY:\n%s", incomingSocketContent.getHead(), incomingSocketContent.getBody()));
    }

}
