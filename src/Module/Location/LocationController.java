package Module.Location;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/locations")
public class LocationController {
    @Inject
    private LocationService locationService;

    public LocationController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<LocationEntity> get(@BeanParam SearchLocationEntity searchLocationEntity) {
        return locationService.get(searchLocationEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchLocationEntity searchLocationEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{locationId}")
    public LocationEntity getId(@PathParam("locationId") int locationId) {
        return locationService.get(locationId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LocationEntity create(LocationEntity locationEntity) {
        return locationService.create(locationEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{locationId}")
    public LocationEntity update(@PathParam("locationId") int locationId, LocationEntity locationEntity) {
        return locationService.update(locationId, locationEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{locationId}")
    public void delete(@PathParam("locationId") int locationId) {
        locationService.delete(locationId);
    }

}