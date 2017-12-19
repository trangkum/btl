package Module.TicketRead;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
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
    public List<TicketReadEntity> get(@BeanParam SearchTicketReadEntity searchTicketReadEntity) {
        return ticketReadService.get(searchTicketReadEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketReadEntity searchTicketReadEntity) {
        return 100;
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketReadId}")
//    public TicketReadEntity getId(@PathParam("ticketReadId") int ticketReadId) {
//        return ticketReadService.get(ticketReadId);
//    }

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
    public TicketReadEntity update(@CookieParam("auth-tokenKey") Cookie tokenKey, @PathParam("ticketReadId") int ticketReadId, TicketReadEntity ticketReadEntity) {
        return ticketReadService.update(tokenKey,ticketReadId, ticketReadEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{ticketReadId}")
//    public void delete(@PathParam("ticketReadId") int ticketReadId) {
//        ticketReadService.delete(ticketReadId);
//    }

}