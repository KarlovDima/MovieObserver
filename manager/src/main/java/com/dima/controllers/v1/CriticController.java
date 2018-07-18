package com.dima.controllers.v1;

import com.dima.models.Critic;
import com.dima.services.CriticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/manager/v1/critics")
public class CriticController {
    @Autowired
    private CriticService criticService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCritic(Critic critic) {
        return Response.status(200).entity(criticService.createCritic(critic)).build();
    }
}
