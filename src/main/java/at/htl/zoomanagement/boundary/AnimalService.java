package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.Animal;
import at.htl.zoomanagement.repository.AnimalRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/animal")
public class AnimalService {

    @Inject
    AnimalRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addElement(Animal animal) {
        if (repository.add(animal) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(animal).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Animal> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Animal readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modifyElement(@PathParam("id") long id, Animal animal) {
        if (repository.modify(id, animal) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(animal).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteElement(@PathParam("id") long id) {
        Animal deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
