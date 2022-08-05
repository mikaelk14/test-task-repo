package com.service.gateway.util;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class MessageGenerator {
    private final int size = 200000;

    public byte [] generateByteArray() {
        try {
            byte[] arr = new byte[size];
            SecureRandom.getInstanceStrong().nextBytes(arr);
            return arr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[size];
    }
}
