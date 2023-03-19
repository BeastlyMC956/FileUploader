package com.beastlymc.fileuploader.controller;

import com.beastlymc.fileuploader.db.EncryptedFile;
import com.beastlymc.fileuploader.request.EncryptedFileRequest;
import com.beastlymc.fileuploader.service.EncryptedFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EncryptedFileControllerTest {

    @Mock
    private EncryptedFileService fileService;

    @InjectMocks
    private EncryptedFileController fileController;

    private EncryptedFile file1;
    private EncryptedFile file2;
    private EncryptedFileRequest request;

    @BeforeEach
    void setUp() {
        file1 = new EncryptedFile("file1.txt", "content1".getBytes());
        file1.setId(1L);
        file2 = new EncryptedFile("file2.txt", "content2".getBytes());
        file2.setId(2L);
        request = new EncryptedFileRequest("file3.txt", "content3".getBytes(), "cq3pw9XjBksDIs4s");
    }

    @Test
    void testGetFiles() {
        when(fileService.getAllEncryptedFiles()).thenReturn(Arrays.asList(file1, file2));

        List<EncryptedFile> result = fileController.getFiles();

        assertEquals(2, result.size());
        verify(fileService, times(1)).getAllEncryptedFiles();
    }

    @Test
    void testGetFileByID_found() {
        when(fileService.getEncryptedFile(1L)).thenReturn(new ResponseEntity<>(file1, HttpStatus.FOUND));

        ResponseEntity<EncryptedFile> result = fileController.getFileByID(1L);

        assertEquals(HttpStatus.FOUND, result.getStatusCode());
        assertEquals(file1, result.getBody());
        verify(fileService, times(1)).getEncryptedFile(1L);
    }

    @Test
    void testGetFileByID_notFound() {
        when(fileService.getEncryptedFile(3L)).thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        ResponseEntity<EncryptedFile> result = fileController.getFileByID(3L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(fileService, times(1)).getEncryptedFile(3L);
    }

    @Test
    void testAddEncryptedFile() {
        when(fileService.uploadFile(request)).thenReturn(new ResponseEntity<>(3L, HttpStatus.CREATED));

        ResponseEntity<Long> result = fileController.addEncryptedFile(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(3L, result.getBody());
        verify(fileService, times(1)).uploadFile(request);
    }
}
