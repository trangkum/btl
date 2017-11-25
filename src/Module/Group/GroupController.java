package Module.Group;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class GroupController {
    @Inject
    private GroupService groupService;

    public GroupController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<GroupEntity> get(@BeanParam SearchGroupModel searchGroupModel) {
        return groupService.get(searchGroupModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchGroupModel searchGroupModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public GroupEntity getId(@PathParam("edgeId") int edgeId) {
        return groupService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GroupEntity create(GroupEntity groupEntity) {
        return groupService.create(groupEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public GroupEntity update(@PathParam("edgeId") int edgeId, GroupEntity groupEntity) {
        return groupService.update(edgeId, groupEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        groupService.delete(edgeId);
    }

}