package com.beastlymc.fileuploader.db;

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
    private String fileName;
    private byte[] fileContent;

    public EncryptedFile(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public EncryptedFile() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EncryptedFile that = (EncryptedFile) o;
        return Objects.equals(id, that.id) && Objects.equals(fileName,
                                                             that.fileName) && Arrays.equals(
                fileContent, that.fileContent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileName);
        result = 31 * result + Arrays.hashCode(fileContent);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
