package com.dima.controllers.v1;

import com.dima.models.Critic;
import com.dima.services.CriticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/manager/v1/critics")
public class CriticController {
    @Autowired
    private CriticService criticService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCritics() {
        return Response.status(200).entity(criticService.getAllCritics()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCritic(Critic critic) {
        return Response.status(200).entity(criticService.createCritic(critic)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCriticById(@PathParam("id") int id) {
        return Response.status(200).entity(criticService.getCriticById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCritic(@PathParam("id") int id, Critic critic) {
        return Response.status(200).entity(criticService.updateCritic(id, critic)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCritic(@PathParam("id") int id) {
        return Response.status(200).entity(criticService.deleteCritic(id)).build();
    }
}
