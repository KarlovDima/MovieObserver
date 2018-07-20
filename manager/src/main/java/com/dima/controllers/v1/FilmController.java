package com.dima.controllers.v1;

import com.dima.models.Film;
import com.dima.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Response.status(200).entity(filmService.createFilm(film)).build();
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
        return Response.status(200).entity(filmService.getFilmById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") int id, Film film) {
        return Response.status(200).entity(filmService.updateFilm(id, film)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFilm(@PathParam("id") int id) {
        return Response.status(200).entity(filmService.deleteFilm(id)).build();
    }
}
