/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.Server;

/**
 *
 * @author ricar
 */
public class JSONRegisterTemplate {

    private String fullName;
    private String email;
    private char[] password;

    public JSONRegisterTemplate(String fullName, String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }
}
