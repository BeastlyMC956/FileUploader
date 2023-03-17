package com.beastlymc.fileuploader.utils;

import com.beastlymc.fileuploader.exception.CryptoException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class EncryptionUtility {

    public static byte[] encryptFile(byte[] inputBytes, String key) throws CryptoException {
        return getBytes(inputBytes, key, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decryptFile(byte[] inputBytes, String key) throws CryptoException {
        return  getBytes(inputBytes, key, Cipher.DECRYPT_MODE);
    }

    public static byte[] getBytes(byte[] inputBytes, String key, int cipherType) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherType, secretKey);

            return cipher.doFinal(inputBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            throw new CryptoException("Error " + (cipherType == Cipher.ENCRYPT_MODE ? "encrypting" : "decrypting") + " file", e);
        }
    }

}
