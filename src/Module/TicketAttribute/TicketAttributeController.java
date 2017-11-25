package Module.TicketAttribute;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
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
    @Path("{edgeId}")
    public TicketAttributeEntity getId(@PathParam("edgeId") int edgeId) {
        return ticketAttributeService.get(edgeId);
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
    @Path("{edgeId}")
    public TicketAttributeEntity update(@PathParam("edgeId") int edgeId, TicketAttributeEntity ticketAttributeEntity) {
        return ticketAttributeService.update(edgeId, ticketAttributeEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        ticketAttributeService.delete(edgeId);
    }

}