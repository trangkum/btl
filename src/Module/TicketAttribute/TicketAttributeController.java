package Module.TicketAttribute;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ticketAttributes")
public class TicketAttributeController {
    @Inject
    private TicketAttributeService ticketAttributeService;

    public TicketAttributeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketAttributeEntity> get(@BeanParam SearchTicketAttributeModel searchTicketAttributeModel) {
        return ticketAttributeService.get(searchTicketAttributeModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketAttributeModel searchTicketAttributeModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketAttributeId}")
    public TicketAttributeEntity getId(@PathParam("ticketAttributeId") int ticketAttributeId) {
        return ticketAttributeService.get(ticketAttributeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketAttributeEntity create(TicketAttributeEntity ticketAttributeEntity) {
        return ticketAttributeService.create(ticketAttributeEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketAttributeId}")
    public TicketAttributeEntity update(@PathParam("ticketAttributeId") int ticketAttributeId, TicketAttributeEntity ticketAttributeEntity) {
        return ticketAttributeService.update(ticketAttributeId, ticketAttributeEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketAttributeId}")
    public void delete(@PathParam("ticketAttributeId") int ticketAttributeId) {
        ticketAttributeService.delete(ticketAttributeId);
    }

}