package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.FoodRation;
import at.htl.zoomanagement.repository.FoodRationRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/foodRation")
public class FoodRationService {

    @Inject
    FoodRationRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addElement(FoodRation foodRation) {
        if (repository.add(foodRation) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(foodRation).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<FoodRation> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public FoodRation readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<FoodRation> readElementById(@QueryParam("species") String species) {
        return repository.getFoodRationsForSpecies(species);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modifyElement(@PathParam("id") long id, FoodRation foodRation) {
        if (repository.modify(id, foodRation) == -1) {
            return Response.notModified("Resource is null!").build();
        }

        return Response.accepted(foodRation).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteElement(@PathParam("id") long id) {
        FoodRation deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
