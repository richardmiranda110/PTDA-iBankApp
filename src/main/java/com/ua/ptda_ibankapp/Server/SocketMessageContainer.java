/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.Server;

/**
 *
 * @author ricar
 */
public class SocketMessageContainer {

    private String head;
    private String body;

    public SocketMessageContainer(String head, String message) {
        this.head = head;
        this.body = message;

    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

}
