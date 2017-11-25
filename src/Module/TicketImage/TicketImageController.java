package Module.TicketImage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class TicketImageController {
    @Inject
    private TicketImageService ticketImageService;

    public TicketImageController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketImageEntity> get(@BeanParam SearchTicketImageModel searchTicketImageModel) {
        return ticketImageService.get(searchTicketImageModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketImageModel searchTicketImageModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketImageEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketImageService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketImageEntity create(TicketImageEntity ticketImageEntity) {
        return ticketImageService.create(ticketImageEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketImageEntity update(@PathParam("edgeId") int edgeId, TicketImageEntity ticketImageEntity) {
        return ticketImageService.update(edgeId, ticketImageEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketImageService.delete(edgeId);
    }

}