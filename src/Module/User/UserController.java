package Module.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserController {
    @Inject
    private UserService userService;

    public UserController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<UserEntity> get(@BeanParam SearchUserModel searchUserModel) {
        return userService.get(searchUserModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchUserModel searchUserModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userName}")
    public UserEntity getId(@PathParam("userName") String userName) {
        return userService.get(userName);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(UserEntity userEntity) {
        return userService.create(userEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userName}")
    public UserEntity update(@PathParam("userName") String userName, UserEntity userEntity) {
        return userService.update(userName, userEntity);
    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{userId}")
//    public void delete(@PathParam("userId") int userId) {
//        userService.delete(userId);
//    }

}