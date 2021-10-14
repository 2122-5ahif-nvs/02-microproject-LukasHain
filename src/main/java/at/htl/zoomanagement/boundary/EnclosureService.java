package at.htl.zoomanagement.boundary;

import at.htl.zoomanagement.entity.Enclosure;
import at.htl.zoomanagement.repository.EnclosureRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/enclosure")
@Tag(name = "Encolsures")
public class EnclosureService {

    @Inject
    EnclosureRepository repository;

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Add an enclosure to database")
    public Response addElement(Enclosure enclosure) {
        if (repository.add(enclosure) == -1) {
            return Response.notModified("Resource is null!").build();
        }
        return Response.accepted(enclosure).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Real all enclosures from database")
    public List<Enclosure> readAllElements() {
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Read one enclosure from database")
    public Enclosure readElementById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Update one enclosure in database")
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
    @Operation(summary = "Delete one enclosure from database")
    public Response deleteElement(@PathParam("id") long id) {
        Enclosure deleted = repository.findById(id);
        if (repository.delete(id)) {
            return Response.accepted(deleted).build();
        }
        return Response.notModified("Resource to delete does not exist!").build();
    }
}
