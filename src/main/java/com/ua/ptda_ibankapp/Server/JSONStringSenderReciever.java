/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
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
    private JSONStringObject recieveIncomingMessage(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        InputStream in = socketChannel.socket().getInputStream();

        ScatteringByteChannel scatter = socketChannel;

        if ((socketChannel.read(buffer)) == 0) {
            try {
                throw new Exception("NO DATA RECIEVED");
            } catch (Exception ex) {
                return null;
            }
        }

        StringBuilder head = null;
        StringBuilder body = null;

        while (buffer.hasRemaining()) {
            buffer.flip();

            head = new StringBuilder();
            body = new StringBuilder();
            boolean limiterFlagRaised = false;

            while (buffer.hasRemaining()) {
                char c = (char) buffer.get();

                if (c == CHAR_LIMITER) {
                    limiterFlagRaised = true;
                    continue;
                }

                if (!limiterFlagRaised) {
                    head.append(c);
                } else {
                    body.append(c);
                }
            }
        }

        return new JSONStringObject(head.toString(), body.toString());

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
                try {
                    throw new Exception("Sent 0 or not able to send Message");
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
