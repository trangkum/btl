package Module.View;

import Module.Ticket.SearchTicketEntity;
import Module.Ticket.TicketEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/view")
public class ViewController {
    @Inject
    private ViewService viewService;

    public ViewController() {

    }

    @GET
    @Path("{viewType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketEntity> get(@PathParam("viewType") String viewType,@CookieParam("auth-tokenKey") Cookie tokenKey,@BeanParam SearchTicketEntity searchTicketEntity) {
        return viewService.get(viewType,tokenKey,searchTicketEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{viewType}/count")
    public Long count(@PathParam("viewType") String viewType,@CookieParam("auth-tokenKey") Cookie tokenKey,@BeanParam SearchTicketEntity searchTicketEntity) {
        return viewService.count(viewType,tokenKey,searchTicketEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{viewType}/countUnread")
    public CountUnread countUnread(@PathParam("viewType") String viewType,@CookieParam("auth-tokenKey") Cookie tokenKey,@BeanParam SearchTicketEntity searchTicketEntity) {
        return viewService.countUnread(viewType,tokenKey,searchTicketEntity);
    }


}