package Module.Team;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/edges")
public class TeamController {
    @Inject
    private TeamService teamService;

    public TeamController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TeamEntity> get(@BeanParam SearchTeamModel searchTeamModel) {
        return teamService.get(searchTeamModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchTeamModel searchTeamModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TeamEntity getId(@PathParam("edgeId") int edgeId) {
        return teamService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TeamEntity create(TeamEntity teamEntity) {
        return teamService.create(teamEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public TeamEntity update(@PathParam("edgeId") int edgeId, TeamEntity teamEntity) {
        return teamService.update(edgeId, teamEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        teamService.delete(edgeId);
    }

}