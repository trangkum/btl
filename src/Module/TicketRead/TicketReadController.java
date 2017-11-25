package Module.TicketRead;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
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
    @Path("{edgeId}")
    public TicketReadEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketReadService.get(edgeId);
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
    @Path("{edgeId}")
    public TicketReadEntity update(@PathParam("edgeId") int edgeId, TicketReadEntity ticketReadEntity) {
        return ticketReadService.update(edgeId, ticketReadEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketReadService.delete(edgeId);
    }

}