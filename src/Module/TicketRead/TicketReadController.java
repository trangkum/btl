package Module.TicketRead;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ticketReads")
public class TicketReadController {
    @Inject
    private TicketReadService ticketReadService;

    public TicketReadController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketReadEntity> get(@BeanParam SearchTicketReadModel searchTicketReadModel) {
        return ticketReadService.get(searchTicketReadModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketReadModel searchTicketReadModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketReadId}")
    public TicketReadEntity getId(@PathParam("ticketReadId") int ticketReadId) {
        return ticketReadService.get(ticketReadId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketReadEntity create(TicketReadEntity ticketReadEntity) {
        return ticketReadService.create(ticketReadEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketReadId}")
    public TicketReadEntity update(@PathParam("ticketReadId") int ticketReadId, TicketReadEntity ticketReadEntity) {
        return ticketReadService.update(ticketReadId, ticketReadEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketReadId}")
//    public void delete(@PathParam("ticketReadId") int ticketReadId) {
//        ticketReadService.delete(ticketReadId);
//    }

}