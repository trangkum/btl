package Module.Permission;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/permissions")
public class PermissionController {
    @Inject
    private PermissionService permissionService;

    public PermissionController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PermissionEntity> get(@BeanParam SearchPermissionEntity searchPermissionEntity) {
        return permissionService.get(searchPermissionEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchPermissionEntity searchPermissionEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{permissionId}")
    public PermissionEntity getId(@PathParam("permissionId") int permissionId) {
        return permissionService.get(permissionId);
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
    @Path("{permissionId}")
    public PermissionEntity update(@PathParam("permissionId") int permissionId, PermissionEntity permissionEntity) {
        return permissionService.update(permissionId, permissionEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{permissionId}")
    public void delete(@PathParam("permissionId") int permissionId) {
        permissionService.delete(permissionId);
    }

}