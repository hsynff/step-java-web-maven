package com.step.course.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    public static String inputToHash(String input){
        input = inputToSha(input);
        String output = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] outputBytes = md.digest(input.getBytes());

            output = DatatypeConverter.printBase64Binary(outputBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return output;
    }

    private static String inputToSha(String input){
        String output = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] outputBytes = md.digest(input.getBytes());

            output = DatatypeConverter.printBase64Binary(outputBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return output;
    }

}
