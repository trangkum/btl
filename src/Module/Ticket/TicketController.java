package Module.Ticket;

import Module.TicketThread.TicketThreadEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tickets")
public class TicketController {
    @Inject
    private TicketService ticketService;

    public TicketController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketEntity> get(@BeanParam SearchTicketEntity searchTicketEntity) {
        return ticketService.get(searchTicketEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTicketEntity searchTicketEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketId}")
    public TicketEntity getId(@PathParam("ticketId") int ticketId) {
        return ticketService.getFullInfo(ticketId);
    }

    @POST
    @Path("{ticketId}/ticketThreads/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketThreadEntity addThread(@CookieParam("auth-tokenKey") Cookie tokenKey, @PathParam("ticketId") Integer ticketId, TicketThreadEntity ticketThreadEntity) {
        return ticketService.addThread(tokenKey, ticketId, ticketThreadEntity);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketEntity create(@CookieParam("auth-tokenKey") Cookie tokenKey, TicketEntity ticketEntity) {
        return ticketService.create(tokenKey, ticketEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketId}")
    public TicketEntity update(@CookieParam("auth-tokenKey") Cookie tokenKey, @PathParam("ticketId") int ticketId, TicketEntity ticketEntity) {
        return ticketService.update(tokenKey, ticketId, ticketEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{ticketId}")
    public void delete(@PathParam("ticketId") int ticketId) {
        ticketService.delete(ticketId);
    }

}