package com.dima;

import com.dima.models.Critic;
import com.dima.models.Film;
import com.dima.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CriticRegistrar {
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
        getById();
    }

    private void create() {
        Critic critic = restTemplateBuilder.build().postForObject("http://Carlitto-PC:8080/manager/v1/critics", Critic.builder()
                .name(name)
                .workBeginning(workBeginning)
                .workEnding(workEnding)
                .build(), Critic.class);

        logService.log("Registered object {}", critic);
    }

    private void getAll() {
        ResponseEntity<Critic[]> responseEntity = restTemplateBuilder.build().getForEntity("http://Carlitto-PC:8080/manager/v1/critics", Critic[].class);

        logService.log("Registered object {}", Arrays.asList(responseEntity.getBody()));
    }

    private void update() {
        Critic critic = Critic.builder()
                .name(name)
                .workBeginning(workBeginning)
                .workEnding(workEnding)
                .build();
        HttpEntity<Critic> criticHttpEntity = new HttpEntity<>(critic);
        ResponseEntity<Critic> responseEntity = restTemplateBuilder.build().exchange("http://Carlitto-PC:8080/manager/v1/critics/{id}", HttpMethod.PUT, criticHttpEntity, Critic.class, 1);

        logService.log("Registered object {}", responseEntity.getBody());
    }

    private void getById() {
        Critic critic = restTemplateBuilder.build().getForObject(managerURL + "/manager/v1/critics/{id}", Critic.class, 34);

        logService.log("Registered object {}", critic);
    }

    private void delete() {
        restTemplateBuilder.build().delete("http://Carlitto-PC:8080/manager/v1/critics/{id}", 1);

        logService.log("Delete object");
    }

    private void addReview() {
        Film film = restTemplateBuilder.build().getForObject("http://Carlitto-PC:8080/manager/v1/films/{id}", Film.class, 33);
        Critic critic = restTemplateBuilder.build().getForObject("http://Carlitto-PC:8080/manager/v1/critics/{id}", Critic.class, 33);

        Review review = restTemplateBuilder.build().postForObject("http://Carlitto-PC:8080/manager/v1/reviews", Review.builder()
                .film(film)
                .critic(critic)
                .comment("Good film!")
                .build(), Review.class);

        logService.log("Registered object {}", review);
    }

    private void addFilmScreening() {

    }
}
