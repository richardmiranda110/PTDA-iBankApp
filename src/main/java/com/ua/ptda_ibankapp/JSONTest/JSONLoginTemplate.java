/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.JSONTest;

import com.ua.ptda_ibankapp.Server.*;

/**
 *
 * @author ricar
 */
public class JSONLoginTemplate {

    private String email;
    private String password;

    public JSONLoginTemplate(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
