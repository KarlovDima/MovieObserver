package com.dima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

@Component
public class Watcher {
    @Value("${folder.path}")
    private String folderPath;

    @Autowired
    private FilmParser filmParser;

    @Autowired
    private FilmRegistrar filmRegistrar;

    public void watch() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(folderPath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    filmRegistrar.register(filmParser.parse(folderPath + "/" + event.context().toString()));
                }
                watchKey.reset();
            }
        } catch (IOException | InterruptedException exc) {
            exc.printStackTrace();
        }

    }
}
