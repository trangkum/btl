package Module.Ticket;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class TicketController {
    @Inject
    private TicketService ticketService;

    public TicketController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketEntity> get(@BeanParam SearchTicketModel searchTicketModel) {
        return ticketService.get(searchTicketModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketModel searchTicketModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketEntity create(TicketEntity ticketEntity) {
        return ticketService.create(ticketEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketEntity update(@PathParam("edgeId") int edgeId, TicketEntity ticketEntity) {
        return ticketService.update(edgeId, ticketEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketService.delete(edgeId);
    }

}