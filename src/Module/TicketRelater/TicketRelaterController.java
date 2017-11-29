package Module.TicketRelater;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ticketRelaters")
public class TicketRelaterController {
    @Inject
    private TicketRelaterService ticketRelaterService;

    public TicketRelaterController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketRelaterEntity> get(@BeanParam SearchTicketRelaterEntity searchTicketRelaterEntity) {
        return ticketRelaterService.get(searchTicketRelaterEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketRelaterEntity searchTicketRelaterEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketRelaterId}")
    public TicketRelaterEntity getId(@PathParam("ticketRelaterId") int ticketRelaterId) {
        return ticketRelaterService.get(ticketRelaterId);
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
    @Path("{ticketRelaterId}")
    public TicketRelaterEntity update(@PathParam("ticketRelaterId") int ticketRelaterId, TicketRelaterEntity ticketRelaterEntity) {
        return ticketRelaterService.update(ticketRelaterId, ticketRelaterEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketRelaterId}")
//    public void delete(@PathParam("ticketRelaterId") int ticketRelaterId) {
//        ticketRelaterService.delete(ticketRelaterId);
//    }

}