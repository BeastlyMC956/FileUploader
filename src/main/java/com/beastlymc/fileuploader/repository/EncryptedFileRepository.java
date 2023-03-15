package com.beastlymc.fileuploader.repository;

import com.beastlymc.fileuploader.sql.EncryptedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptedFileRepository extends JpaRepository<EncryptedFile, Long> {}
