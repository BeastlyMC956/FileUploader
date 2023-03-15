package com.beastlymc.fileuploader.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class EncryptedFile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String filePath;
        private byte[] fileContent;
        private String encryptionKey;

    public EncryptedFile(String filePath, byte[] fileContent, String encryptionKey) {
        this.filePath = filePath;
        this.fileContent = fileContent;
        this.encryptionKey = encryptionKey;
    }

    public EncryptedFile() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptedFile that = (EncryptedFile) o;
        return Objects.equals(id, that.id) && Objects.equals(filePath,
                                                             that.filePath) && Arrays.equals(
                fileContent, that.fileContent) && Objects.equals(encryptionKey, that.encryptionKey);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, encryptionKey);
        result = 31 * result + Arrays.hashCode(fileContent);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
