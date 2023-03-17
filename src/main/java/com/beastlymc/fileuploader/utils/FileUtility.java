package com.beastlymc.fileuploader.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileUtility {

    public static void createFile(Path directory, String fileName, byte[] fileContents) throws IOException {
        if (!directory.toFile().exists()) {
            if (!directory.toFile().mkdirs()) {
                throw new RuntimeException("Could not create the directory");
            }
        }

        File localFile = new File(directory.toFile(), fileName);

        Files.write(localFile.toPath(), fileContents);
    }

    public static long getFolderSize(Path directory) throws IOException {
        try (Stream<Path> fileStream = Files.list(directory)) {
            return fileStream.filter(file -> file.toFile().isFile()).count();
        }
    }
}
