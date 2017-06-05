package com.example.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by Tomek on 04.06.2017.
 */
public interface FileService {
    File saveFile(MultipartFile multipartFile);
}
