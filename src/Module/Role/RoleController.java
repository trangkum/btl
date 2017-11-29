package Module.Role;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/roles")
public class RoleController {
    @Inject
    private RoleService roleService;

    public RoleController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RoleEntity> get(@BeanParam SearchRoleEntity searchRoleEntity) {
        return roleService.get(searchRoleEntity);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchRoleEntity searchRoleEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{roleId}")
    public RoleEntity getId(@PathParam("roleId") int roleId) {
        return roleService.get(roleId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RoleEntity create(RoleEntity roleEntity) {
        return roleService.create(roleEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{roleId}")
    public RoleEntity update(@PathParam("roleId") int roleId, RoleEntity roleEntity) {
        return roleService.update(roleId, roleEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{roleId}")
    public void delete(@PathParam("roleId") int roleId) {
        roleService.delete(roleId);
    }

}