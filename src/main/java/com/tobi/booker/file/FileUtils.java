package com.tobi.booker.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String bookCover) {
        if(StringUtils.isBlank(bookCover)) {
            return null;
        }
        try {
            Path filePath = new File(bookCover).toPath();
            return Files.readAllBytes(filePath);
        } catch(IOException e) {
            log.warn("No file found in the path {}", bookCover, e);
            return null;
        }
    }
}
