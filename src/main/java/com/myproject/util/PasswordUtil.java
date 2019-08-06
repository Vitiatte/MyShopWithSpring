package com.myproject.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtil {

    private static final int SALT_SIZE = 8;
    private static final int NUMBER_OF_ITERATION = 4;
    private static final int KEY_LENGTH = 256;

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[SALT_SIZE];
        random.nextBytes(bytes);
        return byteToString(bytes);
    }

    public static String encryptPassWithSalt(String pass, String salt) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(pass.toCharArray(), stringToByte(salt),
                    NUMBER_OF_ITERATION,
                    KEY_LENGTH);
            SecretKey key = secretKeyFactory.generateSecret(spec);
            byte[] res = key.getEncoded();
            return byteToString(res);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteToString(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    private static byte[] stringToByte(String input) {
        if (Base64.isBase64(input)) {
            return Base64.decodeBase64(input);
        } else {
            return Base64.encodeBase64(input.getBytes());
        }
    }
}
