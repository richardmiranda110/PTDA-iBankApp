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
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        // create Java IO buffer, could be a normal buffer, we're just recieving a message from socket
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {

            //if theres no data, throw excaption
            if ((socketChannel.read(buffer)) == 0) {
                throw new Exception("NO DATA RECIEVED");
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }

        //enable buffer read mode
        buffer.flip();

        //separate packet content with split, more performant
        //return in the right format
        return new SocketMessageContainer((new String(buffer.array())).split("\t"));

    }

    /**
     * This function prepares the data to be sent in the form of a JSON String
     *
     * @param message packet to be sent using the JSONStringObject class
     * @param socketChannel a socket channel bound to a port and ready to send
     */
    private void sendObject(JSONStringObject message, SocketChannel socketChannel) {

        StringJoiner strJoiner = new StringJoiner("\t");
        strJoiner.add(message.getHead());
        strJoiner.add(message.getBody());

        ByteBuffer buffer = ByteBuffer.wrap(strJoiner.toString().getBytes());

        try {
            socketChannel.write(buffer);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
