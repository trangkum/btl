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
    public List<UserEntity> get(@BeanParam SearchUserEntity searchUserEntity) {
        List<UserEntity> userEntities = userService.get(searchUserEntity);
        return userEntities;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchUserEntity searchUserEntity) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public UserEntity getId(@PathParam("userId") Integer userId) {
        return userService.get(userId);
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