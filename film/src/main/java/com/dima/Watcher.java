package com.dima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
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

    @Autowired
    private FilmProcessor filmProcessor;

    public void watch() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(folderPath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    File file = new File(folderPath + event.context().toString());
                    filmRegistrar.register(filmParser.parse(file));
                    filmProcessor.processFilm(file);
                }
                watchKey.reset();
            }
        } catch (IOException | InterruptedException exc) {
            exc.printStackTrace();
        }

    }
}
