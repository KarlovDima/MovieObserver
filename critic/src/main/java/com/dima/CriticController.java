package com.dima;

import com.dima.models.dto.ReviewDTO;
import com.dima.models.entity.Review;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/critic/v1/critics")
public class CriticController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response writeReview(ReviewDTO review) {
        return Response.status(200).entity(ReviewDTO.builder()
                .film(review.getFilm())
                .critic(review.getCritic())
                .comment("Review from " + review.getCritic().getName() + " to " + review.getFilm().getName()).build()).build();
    }
}
