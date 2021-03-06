package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.Food;
import at.htl.zoomanagement.repository.FoodRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/food")
@Tag(name = "Foods")
public class FoodService {

    @Inject
    FoodRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add a type of food to database")
    public Response addElement(Food food) {
        if (repository.add(food) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(food).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read all types of food from database")
    public List<Food> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read one type of food from database")
    public Food readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Updates one type of food in database")
    public Response modifyElement(@PathParam("id") long id, Food food) {
        if (repository.modify(id, food) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(food).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "deletes one type of food from database")
    public Response deleteElement(@PathParam("id") long id) {
        Food deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
