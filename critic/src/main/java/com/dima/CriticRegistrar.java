package com.dima;

import com.dima.models.dto.CriticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CriticRegistrar {
    @Value("${server.port}")
    private int port;

    @Value("${server.host}")
    private String host;

    @Value("${id}")
    private int id;

    @Value("${name}")
    private String name;

    @Value("${workBeginning}")
    private String workBeginning;

    @Value("${workEnding}")
    private String workEnding;

    @Value("${manager.base.url}")
    private String managerURL;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private LogService logService;

    public void register() {
        updateHostAndPort();
    }

    private void updateHostAndPort() {
        CriticDTO criticDTO = restTemplateBuilder.build().getForObject(managerURL + "/manager/v1/critics/{id}", CriticDTO.class, id);
        criticDTO.setHost(host);
        criticDTO.setPort(port);
        HttpEntity<CriticDTO> criticHttpEntity = new HttpEntity<>(criticDTO);
        ResponseEntity<CriticDTO> responseEntity = restTemplateBuilder.build().exchange(managerURL + "/manager/v1/critics/{id}", HttpMethod.PUT, criticHttpEntity, CriticDTO.class, id);

        logService.log("Registered object {}", responseEntity.getBody());
    }
}
