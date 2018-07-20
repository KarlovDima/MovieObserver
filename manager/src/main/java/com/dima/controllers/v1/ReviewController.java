package com.dima.controllers.v1;

import com.dima.models.Review;
import com.dima.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/manager/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReview(Review review) {
        return Response.status(200).entity(reviewService.createReview(review)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReviews() {
        return Response.status(200).entity(reviewService.getAllReviews()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewById(@PathParam("id") int id) {
        return Response.status(200).entity(reviewService.getReviewById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReview(@PathParam("id") int id, Review review) {
        return Response.status(200).entity(reviewService.updateReview(id, review)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReview(@PathParam("id") int id) {
        return Response.status(200).entity(reviewService.deleteReview(id)).build();
    }
}
