package com.dima.controllers.v1;

import com.dima.models.dto.ResponseMessage;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.dto.FilmScreeningDTO;
import com.dima.models.entity.FilmScreening;
import com.dima.services.FilmScreeningService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

@Controller
@Path("/manager/v1/film-screenings")
public class FilmScreeningController {
    @Autowired
    private FilmScreeningService filmScreeningService;

    @Autowired
    private ModelMapper modelMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilmScreening(FilmScreeningDTO filmScreeningDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(filmScreeningService.createFilmScreening(modelMapper.map(filmScreeningDTO, FilmScreening.class)), FilmScreeningDTO.class)).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The film screening with this time is already exists")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilmScreenings() {
        Type listType = new TypeToken<List<FilmScreeningDTO>>() {
        }.getType();
        return Response.status(200).entity(modelMapper.map(filmScreeningService.getAllFilmScreenings(), listType)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmScreeningById(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(modelMapper.map(filmScreeningService.getFilmScreeningById(id), FilmScreeningDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film screening was found with this id")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilmScreening(@PathParam("id") int id, FilmScreeningDTO filmScreeningDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(filmScreeningService.updateFilmScreening(id, modelMapper.map(filmScreeningDTO, FilmScreening.class)), FilmScreeningDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film screening was found with this id")).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The film screening with this time is already exists")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilmScreening(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(filmScreeningService.deleteFilmScreening(id)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film screening was found with this id")).build();
        }
    }
}
