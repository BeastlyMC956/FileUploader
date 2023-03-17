package com.beastlymc.fileuploader.service;

import com.beastlymc.fileuploader.db.EncryptedFile;
import com.beastlymc.fileuploader.exception.CryptoException;
import com.beastlymc.fileuploader.repository.EncryptedFileRepository;
import com.beastlymc.fileuploader.request.EncryptedFileRequest;
import com.beastlymc.fileuploader.utils.EncryptionUtility;
import com.beastlymc.fileuploader.utils.FileUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EncryptedFileServiceTest {

    @Mock
    private EncryptedFileRepository fileRepository;

    @InjectMocks
    private EncryptedFileService fileService;

    private EncryptedFile file1;
    private EncryptedFile file2;
    private EncryptedFileRequest request;

    @BeforeEach    void setUp() {
        file1 = new EncryptedFile("file1.txt", "content1".getBytes(), "RsUxvchRXo9ifFCJ");
        file1.setId(1L);
        file2 = new EncryptedFile("file2.txt", "content2".getBytes(), "EMZVzwiRckJYpcg3");
        file2.setId(2L);
        request = new EncryptedFileRequest("file3.txt", "content3".getBytes(), "Tojsc9YUxokwP27B");
    }

    @Test
    void testGetAllEncryptedFiles() {
        when(fileRepository.findAll()).thenReturn(Arrays.asList(file1, file2));

        List<EncryptedFile> result = fileService.getAllEncryptedFiles();

        assertEquals(2, result.size());
        verify(fileRepository, times(1)).findAll();
    }

    @Test
    void testGetEncryptedFile_found() {
        when(fileRepository.findById(1L)).thenReturn(Optional.of(file1));

        ResponseEntity<EncryptedFile> result = fileService.getEncryptedFile(1L);

        assertEquals(HttpStatus.FOUND, result.getStatusCode());
        assertEquals(file1, result.getBody());
        verify(fileRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEncryptedFile_notFound() {
        when(fileRepository.findById(3L)).thenReturn(Optional.empty());

        ResponseEntity<EncryptedFile> result = fileService.getEncryptedFile(3L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(fileRepository, times(1)).findById(3L);
    }

    @Test
    void testUploadFile() {
        EncryptedFile encryptedFile = new EncryptedFile(request.fileName(), request.fileContent(),
                                                        request.encryptionKey());
        encryptedFile.setId(3L);

        when(fileRepository.save(any(EncryptedFile.class))).thenReturn(encryptedFile);

        ResponseEntity<Long> result = fileService.uploadFile(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(3L, result.getBody());
        verify(fileRepository, times(1)).save(any(EncryptedFile.class));
    }

    @Test
    void testFileUtility_createFile() throws IOException, CryptoException {
        Path tempDir = Paths.get("D:\\Programming\\file-uploader\\src\\test\\resources");
        String fileName = "tempFile.txt";
        byte[] fileContent = "content".getBytes();
        String encryptionKey = "aZDXEdX6G5FfVo5Z";

        FileUtility.createFile(tempDir, fileName, EncryptionUtility.encryptFile(fileContent, encryptionKey));

        File createdFile = new File(tempDir.toFile(), fileName);
        assertTrue(createdFile.exists());
        byte[] decryptedContent = EncryptionUtility.decryptFile(Files.readAllBytes(createdFile.toPath()), encryptionKey);
        assertEquals(fileContent.length, decryptedContent.length);
    }

    @Test
    void testEncryptionUtility_encryptAndDecryptFile() throws CryptoException {
        byte[] originalContent = "content".getBytes();
        String encryptionKey = "6s4H3WIaATbCzGoI";

        byte[] encryptedContent = EncryptionUtility.encryptFile(originalContent, encryptionKey);
        byte[] decryptedContent = EncryptionUtility.decryptFile(encryptedContent, encryptionKey);

        assertArrayEquals(originalContent, decryptedContent);
    }
}
