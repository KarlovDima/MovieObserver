package com.dima.review.managing;

import com.dima.LogService;
import com.dima.dao.DAOFactory;
import com.dima.models.dto.ReviewDTO;
import com.dima.models.entity.Critic;
import com.dima.models.entity.Film;
import com.dima.models.entity.FilmScreening;
import com.dima.models.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ReviewSender {
    @Autowired
    private DAOFactory daoFactory;

    @Autowired
    private LogService logService;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ModelMapper modelMapper;

    public void sendReview(Film film, FilmScreening filmScreening){
        Critic critic = getFreeCritic(film, film.getDuration(), filmScreening.getTime());
        if (critic == null) {
            logService.log("No free critics available");
            return;
        }
        logService.log("Review created {}", createReview(film, critic));
    }

    private ReviewDTO createReview(Film film, Critic critic) {
        Review review = Review.builder().film(film).critic(critic).build();
        ReviewDTO reviewDTO = restTemplateBuilder.build().postForObject("http://" + review.getCritic().getHost() + ":" + review.getCritic().getPort() + "/critic/v1/critics",
                modelMapper.map(review, ReviewDTO.class), ReviewDTO.class);
        daoFactory.getReviewDAO().createReview(modelMapper.map(reviewDTO, Review.class));

        return reviewDTO;
    }

    private LocalTime getEndTime(String time, String duration) {
        LocalTime filmStarted = LocalTime.parse(time);
        LocalTime filmDuration = LocalTime.parse(duration);
        return filmStarted.plusHours(filmDuration.getHour()).plusMinutes(filmDuration.getMinute());
    }

    private Critic getFreeCritic(Film film, String filmDuration, String time) {
        List<Critic> critics = daoFactory.getCriticDAO().getCriticsWithoutReview(film.getId());
        if (critics.size() != 0)
            for (Critic critic : critics)
                if (isWorking(critic, filmDuration, time))
                    return critic;
        return null;
    }

    private boolean isWorking(Critic critic, String filmDuration, String time) {
        LocalTime workBeginning = LocalTime.parse(critic.getWorkBeginning());
        LocalTime workEnding = LocalTime.parse(critic.getWorkEnding());
        LocalTime filmScreening = LocalTime.parse(time);

        return workBeginning.isBefore(filmScreening) && workEnding.isAfter(getEndTime(time, filmDuration));
    }
}
