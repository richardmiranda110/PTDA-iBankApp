/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.LoginClient;

import java.nio.ByteBuffer;

/**
 *
 * @author ricar
 */
public class SocketMessageContainer {

    private ByteBuffer head;
    private ByteBuffer body;

    public SocketMessageContainer(ByteBuffer head, ByteBuffer message) {
        this.head = head;
        this.body = message;

    }

    public SocketMessageContainer(byte[] wrapToHead, byte[] wrapToBody) {
        head = ByteBuffer.wrap(wrapToHead);
        body = ByteBuffer.wrap(wrapToBody);
    }

    ByteBuffer[] getContainer() {
        return new ByteBuffer[]{head, body};
    }

    public ByteBuffer getHead() {
        return head;
    }

    public ByteBuffer getBody() {
        return body;
    }

}
