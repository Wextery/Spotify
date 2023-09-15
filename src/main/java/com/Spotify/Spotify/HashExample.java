package com.Spotify.Spotify;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashExample {
    public static String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String password = "user123";
        String hashedPassword = hashString(password);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}

