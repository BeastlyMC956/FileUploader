package com.beastlymc.fileuploader;

import com.beastlymc.fileuploader.repository.EncryptedFileRepository;
import com.beastlymc.fileuploader.sql.EncryptedFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class FileUploaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploaderApplication.class, args);
    }

}
