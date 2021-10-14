package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.FoodRation;
import at.htl.zoomanagement.repository.FoodRationRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/foodRation")
@Tag(name = "FoodRotations")
public class FoodRationService {

    @Inject
    FoodRationRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Document feeding of animal to database")
    public Response addElement(FoodRation foodRation) {
        if (repository.add(foodRation) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(foodRation).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read all feedings of animals from database")
    public List<FoodRation> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read one feeding of an animal from database")
    public FoodRation readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read all feedings of a specific species of animals from database")
    public List<FoodRation> readAllElementsBySpecies(@QueryParam("species") String species) {
        return repository.getFoodRationsForSpecies(species);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Update one feeding of an animal in database")
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
    @Operation(summary = "Delete one feeding of an animal from database")
    public Response deleteElement(@PathParam("id") long id) {
        FoodRation deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
