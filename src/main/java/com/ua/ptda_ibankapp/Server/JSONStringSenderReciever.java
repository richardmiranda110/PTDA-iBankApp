/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.SocketChannel;

public class JSONStringSenderReciever {

    private final char CHAR_LIMITER = '\t';

    /**
     * This function prepares the data to be recieved in the form of a JSON
     * String
     *
     * @param socketChannel a socket channel bound to a port and ready to send
     * @return will return the read JSON String
     * @throws IOException
     */
    private SocketMessageContainer recieveIncomingMessage(SocketChannel socketChannel) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try (InputStream in = socketChannel.socket().getInputStream()) {

            if ((socketChannel.read(buffer)) == 0) {
                throw new Exception("NO DATA RECIEVED");
            }

            buffer.flip();
            String[] structure = new String(buffer.array()).split("\t");
            return new SocketMessageContainer(structure[0], structure[1]);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }

    }

    /**
     * This function prepares the data to be sent in the form of a JSON String
     *
     * @param message packet to be sent using the JSONStringObject class
     * @param socketChannel a socket channel bound to a port and ready to send
     */
    private void sendObject(JSONStringObject message, SocketChannel socketChannel) {
        try {
            ByteBuffer header = ByteBuffer.wrap(message.getHead().getBytes());
            ByteBuffer delimiter = ByteBuffer.wrap(new byte[]{CHAR_LIMITER});
            ByteBuffer body = ByteBuffer.wrap(message.getBody().getBytes());

            GatheringByteChannel gather = socketChannel;

            if (gather.write(new ByteBuffer[]{header, delimiter, body}) == 0) {
                throw new Exception("Sent 0 or not able to send Message");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
