package com.dima.controllers.v1;

import com.dima.models.dto.ResponseMessage;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.dto.CriticDTO;
import com.dima.models.entity.Critic;
import com.dima.services.CriticService;
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
@Path("/manager/v1/critics")
public class CriticController {
    @Autowired
    private CriticService criticService;

    @Autowired
    private ModelMapper modelMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCritics() {
        Type listType = new TypeToken<List<CriticDTO>>() {
        }.getType();
        return Response.status(200).entity(modelMapper.map(criticService.getAllCritics(), listType)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCritic(CriticDTO criticDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(criticService.createCritic(modelMapper.map(criticDTO, Critic.class)), CriticDTO.class)).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The critic with this name is already exists")).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCriticById(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(modelMapper.map(criticService.getCriticById(id), CriticDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No critic was found with this id")).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCritic(@PathParam("id") int id, CriticDTO criticDTO) {
        try {
            return Response.status(200).entity(modelMapper.map(criticService.updateCritic(id, modelMapper.map(criticDTO, Critic.class)), CriticDTO.class)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No critic was found with this id")).build();
        } catch (DataIntegrityViolationException exc) {
            return Response.status(400).entity(new ResponseMessage("The critic with this name is already exists")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCritic(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(criticService.deleteCritic(id)).build();
        } catch (ResourceNotFoundException exc) {
            return Response.status(404).entity(new ResponseMessage("No critic was found with this id")).build();
        }
    }
}
