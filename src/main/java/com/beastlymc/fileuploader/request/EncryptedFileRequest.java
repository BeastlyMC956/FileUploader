package com.beastlymc.fileuploader.request;

/**
 * Represents a request to upload an encrypted file.
 */
public record EncryptedFileRequest(String fileName, byte[] fileContent, String encryptionKey) { }
