package com.dima.controllers.v1;

import com.dima.models.dto.ResponseMessage;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.dto.ReviewDTO;
import com.dima.models.entity.Review;
import com.dima.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

@Controller
@Path("/manager/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ModelMapper modelMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReview(ReviewDTO reviewDTO) {
        return Response.status(200).entity(modelMapper.map(reviewService.createReview(modelMapper.map(reviewDTO, Review.class)), ReviewDTO.class)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReviews() {
        Type listType = new TypeToken<List<ReviewDTO>>() {
        }.getType();
        return Response.status(200).entity(modelMapper.map(reviewService.getAllReviews(), listType)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewById(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(modelMapper.map(reviewService.getReviewById(id), ReviewDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No review was found with this id")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReview(@PathParam("id") int id, ReviewDTO reviewDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(reviewService.updateReview(id, modelMapper.map(reviewDTO, Review.class)), ReviewDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No review was found with this id")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReview(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(reviewService.deleteReview(id)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No review was found with this id")).build();
        }
    }
}
