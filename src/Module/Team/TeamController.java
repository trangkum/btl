package Module.Team;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/teams")
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
    @Path("{teamId}")
    public TeamEntity getId(@PathParam("teamId") int teamId) {
        return teamService.get(teamId);
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
    @Path("{teamId}")
    public TeamEntity update(@PathParam("teamId") int teamId, TeamEntity teamEntity) {
        return teamService.update(teamId, teamEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{teamId}")
    public void delete(@PathParam("teamId") int teamId) {
        teamService.delete(teamId);
    }

}