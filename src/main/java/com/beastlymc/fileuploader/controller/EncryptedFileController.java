package com.beastlymc.fileuploader.controller;

import com.beastlymc.fileuploader.db.EncryptedFile;
import com.beastlymc.fileuploader.request.EncryptedFileRequest;
import com.beastlymc.fileuploader.service.EncryptedFileService;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The controller responsible for handling requests related to encrypted files.
 */
@RestController
@RequestMapping("api/v1/encrypted_files")
public class EncryptedFileController {
    private final EncryptedFileService fileService;

    /**
     * Initializes the controller with the given EncryptedFileService.
     *
     * @param fileService The EncryptedFileService instance.
     */
    public EncryptedFileController(EncryptedFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Retrieves a list of all encrypted files.
     *
     * @return A list of all EncryptedFile instances.
     */
    @CrossOrigin
    @GetMapping("/all")
    public List<EncryptedFile> getFiles() {
        return fileService.getAllEncryptedFiles();
    }


    /**
     * Retrieves an encrypted file by its ID.
     *
     * @param id The ID of the encrypted file to retrieve.
     * @return A ResponseEntity containing the EncryptedFile instance if found, or a NOT_FOUND status.
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<EncryptedFile> getFileByID(@PathVariable(value = "id") Long id) {
        return fileService.getEncryptedFile(id);
    }

    @CrossOrigin
    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("file") String fileName) throws IOException {
        return fileService.downloadEncryptedFile(fileName);
    }

    /**
     * Uploads an encrypted file and saves it in the repository.
     *
     * @param request The EncryptedFileRequest containing the file name, content, and encryption key.
     * @return A ResponseEntity containing the ID of the saved file and a CREATED status.
     */
    @CrossOrigin
    @PostMapping("/upload")
    public ResponseEntity<Long> addEncryptedFile(@RequestBody EncryptedFileRequest request) {
        return fileService.uploadFile(request);
    }

}
