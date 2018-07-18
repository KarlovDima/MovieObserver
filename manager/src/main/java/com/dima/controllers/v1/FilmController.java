package com.dima.controllers.v1;

import com.dima.models.Film;
import com.dima.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
