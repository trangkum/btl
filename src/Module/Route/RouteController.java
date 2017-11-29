package Module.Route;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/routes")
public class RouteController {
    @Inject
    private RouteService routeService;

    public RouteController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RouteEntity> get(@BeanParam SearchRouteEntity searchRouteEntity) {
        return routeService.get(searchRouteEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchRouteEntity searchRouteEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{routeId}")
    public RouteEntity getId(@PathParam("routeId") int routeId) {
        return routeService.get(routeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RouteEntity create(RouteEntity routeEntity) {
        return routeService.create(routeEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{routeId}")
    public RouteEntity update(@PathParam("routeId") int routeId, RouteEntity routeEntity) {
        return routeService.update(routeId, routeEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{routeId}")
    public void delete(@PathParam("routeId") int routeId) {
        routeService.delete(routeId);
    }

}