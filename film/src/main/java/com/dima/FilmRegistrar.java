package com.dima;

import com.dima.models.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class FilmRegistrar {
    @Value("${manager.base.url}")
    private String managerURL;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LogService logService;

    public void register(Film film) {
        restTemplateBuilder.build().postForObject(managerURL + "/manager/v1/films", film, Film.class);

        logService.log("Registered object");
    }
}
