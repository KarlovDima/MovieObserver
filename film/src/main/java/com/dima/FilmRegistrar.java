package com.dima;

import com.dima.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class FilmRegistrar {
    @Value("${server.port}")
    private int port;

    @Value("${server.host}")
    private String host;

    @Value("${name}")
    private String name;

    @Value("${duration}")
    private int duration;

    @Value("${active}")
    private boolean active;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LogService logService;

    public void register() {
        Film film = restTemplateBuilder.build().postForObject("http://Carlitto-PC:8080/manager/v1/films", Film.builder()
                .name(name)
                .duration(duration)
                .isActive(active)
                .host(host)
                .port(port)
                .build(), Film.class);

        logService.log("Registered object {}", film);
    }
}
