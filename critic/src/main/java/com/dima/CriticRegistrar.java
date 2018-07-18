package com.dima;

import com.dima.models.Critic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class CriticRegistrar {
    @Value("${server.port}")
    private int port;

    @Value("${server.host}")
    private String host;

    @Value("${name}")
    private String name;

    @Value("${workBeginning}")
    private String workBeginning;

    @Value("${workEnding}")
    private String workEnding;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LogService logService;

    public void register() {
        Critic critic = restTemplateBuilder.build().postForObject("http://Carlitto-PC:8080/manager/v1/critics", Critic.builder()
                .name(name)
                .workBeginning(workBeginning)
                .workEnding(workEnding)
                .host(host)
                .port(port)
                .build(), Critic.class);

        logService.log("Registered object {}", critic);
    }
}
