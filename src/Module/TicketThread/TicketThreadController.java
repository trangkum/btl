package Module.TicketThread;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
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
    @Path("{edgeId}")
    public TicketThreadEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketThreadService.get(edgeId);
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
    @Path("{edgeId}")
    public TicketThreadEntity update(@PathParam("edgeId") int edgeId, TicketThreadEntity ticketThreadEntity) {
        return ticketThreadService.update(edgeId, ticketThreadEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketThreadService.delete(edgeId);
    }

}