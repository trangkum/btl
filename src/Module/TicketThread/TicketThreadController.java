package Module.TicketThread;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ticketThreads")
public class TicketThreadController {
    @Inject
    private TicketThreadService ticketThreadService;

    public TicketThreadController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketThreadEntity> get(@BeanParam SearchTicketThreadModel searchTicketThreadModel) {
        return ticketThreadService.get(searchTicketThreadModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketThreadModel searchTicketThreadModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketThreadId}")
    public TicketThreadEntity getId(@PathParam("ticketThreadId") int ticketThreadId) {
        return ticketThreadService.get(ticketThreadId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketThreadEntity create(TicketThreadEntity ticketThreadEntity) {
        return ticketThreadService.create(ticketThreadEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketThreadId}")
    public TicketThreadEntity update(@PathParam("ticketThreadId") int ticketThreadId, TicketThreadEntity ticketThreadEntity) {
        return ticketThreadService.update(ticketThreadId, ticketThreadEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketThreadId}")
//    public void delete(@PathParam("ticketThreadId") int ticketThreadId) {
//        ticketThreadService.delete(ticketThreadId);
//    }

}