package Module.TicketRelater;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class TicketRelaterController {
    @Inject
    private TicketRelaterService ticketRelaterService;

    public TicketRelaterController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketRelaterEntity> get(@BeanParam SearchTicketRelaterModel searchTicketRelaterModel) {
        return ticketRelaterService.get(searchTicketRelaterModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketRelaterModel searchTicketRelaterModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketRelaterEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketRelaterService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketRelaterEntity create(TicketRelaterEntity ticketRelaterEntity) {
        return ticketRelaterService.create(ticketRelaterEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TicketRelaterEntity update(@PathParam("edgeId") int edgeId, TicketRelaterEntity ticketRelaterEntity) {
        return ticketRelaterService.update(edgeId, ticketRelaterEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketRelaterService.delete(edgeId);
    }

}