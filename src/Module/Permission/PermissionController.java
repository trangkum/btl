package Module.Permission;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class PermissionController {
    @Inject
    private PermissionService permissionService;

    public PermissionController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PermissionEntity> get(@BeanParam SearchPermissionModel searchPermissionModel) {
        return permissionService.get(searchPermissionModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchPermissionModel searchPermissionModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public PermissionEntity getId(@PathParam("edgeId") int edgeId) {
        return permissionService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PermissionEntity create(PermissionEntity permissionEntity) {
        return permissionService.create(permissionEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public PermissionEntity update(@PathParam("edgeId") int edgeId, PermissionEntity permissionEntity) {
        return permissionService.update(edgeId, permissionEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        permissionService.delete(edgeId);
    }

}