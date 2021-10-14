package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.Enclosure;
import at.htl.zoomanagement.repository.EnclosureRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/enclosure")
public class EnclosureService {

    @Inject
    EnclosureRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addElement(Enclosure enclosure) {
        if (repository.add(enclosure) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(enclosure).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Enclosure> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Enclosure readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modifyElement(@PathParam("id") long id, Enclosure enclosure) {
        if (repository.modify(id, enclosure) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(enclosure).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteElement(@PathParam("id") long id) {
        Enclosure deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
