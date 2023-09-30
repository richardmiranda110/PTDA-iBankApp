/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.JSONTest;

/**
 *
 * @author ricar
 */
public class SocketMessageContainer {

    private String head;
    private String body;

    public SocketMessageContainer() {
        this.head = null;
        this.body = null;

    }

    public SocketMessageContainer(String head, String message) {
        this.head = head;
        this.body = message;

    }

    public SocketMessageContainer(String[] strArrayWithContent) {
        this.head = strArrayWithContent[0];
        this.body = strArrayWithContent[1];

    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {

        return String.format("SocketMessageContainer{\n%s\n%s\n}", this.head, this.body);
    }
}

class SocketHead {

    String action;
    String token;

    public SocketHead() {
        action = null;
        token = null;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
