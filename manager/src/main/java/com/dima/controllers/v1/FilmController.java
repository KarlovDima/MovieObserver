package com.dima.controllers.v1;

import com.dima.models.dto.ResponseMessage;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.dto.FilmDTO;
import com.dima.models.entity.Film;
import com.dima.services.FilmService;
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
@Path("/manager/v1/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private ModelMapper modelMapper;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilm(FilmDTO filmDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(filmService.createFilm(modelMapper.map(filmDTO, Film.class)), FilmDTO.class)).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The film with this name is already exists")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        Type listType = new TypeToken<List<FilmDTO>>() {
        }.getType();
        return Response.status(200).entity(modelMapper.map(filmService.getAllFilms(), listType)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmById(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(modelMapper.map(filmService.getFilmById(id), FilmDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film was found with this id")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") int id, FilmDTO filmDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(filmService.updateFilm(id, modelMapper.map(filmDTO, Film.class)), FilmDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film was found with this id")).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The film with this name is already exists")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilm(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(filmService.deleteFilm(id)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film was found with this id")).build();
        }
    }
}
