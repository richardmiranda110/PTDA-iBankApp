/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Server;

import java.nio.ByteBuffer;

/**
 *
 * @author ricar
 */
public class JSONStringObject {

    private String head;
    private String body;

    public JSONStringObject(String head, String message) {
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
