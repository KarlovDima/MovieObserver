package com.dima.controllers.v1;

import com.dima.ResponseMessage;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.Film;
import com.dima.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/manager/v1/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilm(Film film) {
        try {
            return Response.status(200).entity(filmService.createFilm(film)).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The film with this name is already exists")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        return Response.status(200).entity(filmService.getAllFilms()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmById(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(filmService.getFilmById(id)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No film was found with this id")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") int id, Film film) {
        try {
            return Response.status(200).entity(filmService.updateFilm(id, film)).build();
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
