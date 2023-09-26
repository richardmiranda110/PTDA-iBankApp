/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ua.ptda_ibankapp.Server;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author ricar
 */
public class TokenGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static void main(String[] args) {
        System.out.println(generateToken());
    }
}
