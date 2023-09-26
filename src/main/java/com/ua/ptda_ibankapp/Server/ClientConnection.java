package com.ua.ptda_ibankapp.Server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.amazonaws.auth.*;

/**
 *
 * @author ricar
 */
public class ClientConnection {

    //Socket socket;
    BasicSessionCredentials token;
    long tokenLastTimeUsed;
    long tokenCreationTime;

    public ClientConnection() {
        tokenLastTimeUsed = System.currentTimeMillis();
        token = new BasicSessionCredentials("", "", "");
    }
}
