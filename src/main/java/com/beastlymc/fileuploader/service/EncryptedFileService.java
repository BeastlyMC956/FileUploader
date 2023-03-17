package com.beastlymc.fileuploader.service;

import com.beastlymc.fileuploader.db.EncryptedFile;
import com.beastlymc.fileuploader.repository.EncryptedFileRepository;
import com.beastlymc.fileuploader.request.EncryptedFileRequest;
import com.beastlymc.fileuploader.utils.EncryptionUtility;
import com.beastlymc.fileuploader.utils.FileUtility;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EncryptedFileService {

    private final EncryptedFileRepository fileRepository;
    private static final Path DIRECTORY = Paths.get("D:\\Programming\\file-uploader\\src\\main\\resources\\cdn");

    /**
     * Initializes the service with the given EncryptedFileRepository.
     *
     * @param fileRepository The EncryptedFileRepository instance.
     */
    public EncryptedFileService(EncryptedFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Retrieves all encrypted files from the repository.
     *
     * @return A list of all EncryptedFile instances.
     */
    public List<EncryptedFile> getAllEncryptedFiles() {
        return fileRepository.findAll();
    }

    /**
     * Retrieves an encrypted file from the repository by its ID.
     *
     * @param id The ID of the encrypted file to retrieve.
     * @return A ResponseEntity containing the EncryptedFile instance if found, or a NOT_FOUND status.
     */
    public ResponseEntity<EncryptedFile> getEncryptedFile(long id) {
        return fileRepository.findById(id)
                .map(encryptedFile -> new ResponseEntity<>(encryptedFile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Resource> downloadEncryptedFile(String fileName) throws MalformedURLException, FileNotFoundException {
        Path filePath = Paths.get("D:\\Programming\\file-uploader\\src\\main\\resources\\cdn\\" + fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if(!resource.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /**
     * Uploads an encrypted file and saves it in the repository.
     *
     * @param request The EncryptedFileRequest containing the file name, content, and encryption key.
     * @return A ResponseEntity containing the ID of the saved file and a CREATED status.
     * @throws RuntimeException If there is an issue creating the file.
     */
    public ResponseEntity<Long> uploadFile(@NotNull EncryptedFileRequest request) {
        EncryptedFile encryptedFile = new EncryptedFile();
        encryptedFile.setFileName(request.fileName());
        String fileName = getFolderSize() + "_" + request.fileName();
        byte[] encryptedContent = EncryptionUtility.encryptFile(request.fileContent(), request.encryptionKey());

        try {
            FileUtility.createFile(DIRECTORY, fileName, encryptedContent);
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't create a file", exception);
        }

        encryptedFile.setFileContent(encryptedContent);
        encryptedFile.setEncryptionKey(request.encryptionKey());

        return new ResponseEntity<>(fileRepository.save(encryptedFile).getId(), HttpStatus.CREATED);
    }

    /**
     * Gets the size of the directory storing encrypted files.
     *
     * @return The number of files in the directory.
     * @throws RuntimeException If there is an issue getting the folder size.
     */
    private long getFolderSize() {
        try {
            return FileUtility.getFolderSize(DIRECTORY);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }
}
