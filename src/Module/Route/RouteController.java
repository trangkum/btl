package Module.Route;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class RouteController {
    @Inject
    private RouteService routeService;

    public RouteController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RouteEntity> get(@BeanParam SearchRouteModel searchRouteModel) {
        return routeService.get(searchRouteModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchRouteModel searchRouteModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public RouteEntity getId(@PathParam("edgeId") int edgeId) {
        return routeService.get(edgeId);
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
    @Path("{edgeId}")
    public RouteEntity update(@PathParam("edgeId") int edgeId, RouteEntity routeEntity) {
        return routeService.update(edgeId, routeEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        routeService.delete(edgeId);
    }

}