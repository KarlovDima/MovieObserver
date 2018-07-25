package com.dima;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class FilmProcessor {
    public void processFilm(File file) {
        String directoryPath = getDirectoryPath(file);

        createDirectories(directoryPath);
        moveFile(file.getPath(), new StringBuilder().append(directoryPath, 0, directoryPath.lastIndexOf("\\") + 1).append(file.getName()).toString());
    }

    private String getDirectoryPath(File file) {
        return new StringBuilder()
                .append(file.getPath(), 0, file.getPath().lastIndexOf("\\"))
                .append("\\Processed films\\")
                .append(file.getName(), 0, file.getName().lastIndexOf("."))
                .append("\\Reviews")
                .toString();
    }

    private void createDirectories(String directoryPath) {
        File directory = new File(directoryPath);
        directory.mkdirs();
    }

    private void moveFile(String filePath, String targetPath) {
        Path source = Paths.get(filePath);
        Path target = Paths.get(targetPath);
        try {
            Files.move(source, target, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
