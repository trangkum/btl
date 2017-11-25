package Module.TicketImage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ticketImages")
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
    @Path("{ticketImageId}")
    public TicketImageEntity getId(@PathParam("ticketImageId") int ticketImageId) {
        return ticketImageService.get(ticketImageId);
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
    @Path("{ticketImageId}")
    public TicketImageEntity update(@PathParam("ticketImageId") int ticketImageId, TicketImageEntity ticketImageEntity) {
        return ticketImageService.update(ticketImageId, ticketImageEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketImageId}")
//    public void delete(@PathParam("ticketImageId") int ticketImageId) {
//        ticketImageService.delete(ticketImageId);
//    }

}