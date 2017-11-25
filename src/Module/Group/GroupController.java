package Module.Group;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/groups")
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
    @Path("{groupId}")
    public GroupEntity getId(@PathParam("groupId") int groupId) {
        return groupService.get(groupId);
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
    @Path("{groupId}")
    public GroupEntity update(@PathParam("groupId") int groupId, GroupEntity groupEntity) {
        return groupService.update(groupId, groupEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{groupId}")
    public void delete(@PathParam("groupId") int groupId) {
        groupService.delete(groupId);
    }

}