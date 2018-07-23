package com.dima;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private Watcher watcher;

    public static void main(String[] args) {
        setHostAndPort();

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        watcher.watch();
    }

    private static void setHostAndPort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            System.setProperty("server.host", InetAddress.getLocalHost().getHostAddress());
            System.setProperty("server.port", String.valueOf(serverSocket.getLocalPort()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
