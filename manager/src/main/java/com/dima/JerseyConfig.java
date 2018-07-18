package com.dima;

import com.dima.controllers.v1.CriticController;
import com.dima.controllers.v1.FilmController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerClasses(FilmController.class, CriticController.class);
    }
}
