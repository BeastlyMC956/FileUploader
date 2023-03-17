package com.beastlymc.fileuploader.exception;

public class CryptoException extends RuntimeException {
    public CryptoException() {}

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
