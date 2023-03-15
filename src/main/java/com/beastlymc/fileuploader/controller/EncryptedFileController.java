package com.beastlymc.fileuploader.controller;

import com.beastlymc.fileuploader.repository.EncryptedFileRepository;
import com.beastlymc.fileuploader.sql.EncryptedFile;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "api/v1/encrypted_files")
public class EncryptedFileController {
    private final EncryptedFileRepository fileRepository;

    public EncryptedFileController(EncryptedFileRepository fileRepository) {this.fileRepository = fileRepository;}

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<EncryptedFile> getFiles() {
        return fileRepository.findAll();
    }

    record EncryptedFileRequest(byte[] fileContent, String encryptionKey){}

    @CrossOrigin
    @PostMapping(value = "/add")
    public void addEncryptedFile(@RequestBody EncryptedFileRequest request) {
        EncryptedFile encryptedFile = new EncryptedFile();
        // ENCRYPT THE FILE
        encryptedFile.setFilePath("cdn/" + ThreadLocalRandom.current().nextInt(100));
        encryptedFile.setFileContent(request.fileContent);
        encryptedFile.setEncryptionKey(request.encryptionKey);
        fileRepository.save(encryptedFile);

    }
}
