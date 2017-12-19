package Module.Auth;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeService;
import Module.Location.LocationEntity;
import Module.Location.LocationService;
import Module.Location.SearchLocationEntity;
import Module.Permission.PermissionEntity;
import Module.Role.RoleEntity;
import Module.Role.RoleService;
import Module.Role.SearchRoleEntity;
import Module.Route.RouteEntity;
import Module.Route.RouteService;
import Module.Team.SearchTeamEntity;
import Module.Team.TeamEntity;
import Module.Team.TeamService;
import Module.User.TokenEntity;
import Module.User.TokenService;
import Module.User.UserEntity;
import Module.User.UserService;
import com.mysql.cj.core.util.StringUtils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.isNullOrEmpty;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;
    @Inject
    private UserService userService;
    @Inject
    private TokenService tokenService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private LocationService locationService;
    @Inject
    private RoleService roleService;
    @Inject
    private RouteService routeService;
    @Inject
    private TeamService teamService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if (path.startsWith("authentication/")) return;
//        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
//            requestContext.abortWith(Response.status(Response.Status.OK).build());
//            return;
//        }
        Cookie cookieTokenKey = requestContext.getCookies().get("auth-tokenKey");
        // Then check is the service key exists and is valid.
        if (cookieTokenKey == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        String tokenKey = cookieTokenKey.getValue();
        if (tokenKey == null || tokenKey.trim().equals("")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey);
        if (tokenEntity == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        UserEntity userEntity = userService.get(tokenEntity.userId);
        if (userEntity == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        EmployeeEntity employeeEntity = employeeService.get(userEntity.employeeId);
        if (employeeEntity == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        String link = parseLink(path);
        String method = requestContext.getRequest().getMethod();
        RouteEntity routeEntity = routeService.get(method, link);
        if (routeEntity == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
            return;
        }
        SearchRoleEntity searchRoleEntity = new SearchRoleEntity();
        List<RoleEntity> roleEntities = roleService.get(searchRoleEntity);
        roleEntities = roleEntities.parallelStream().filter(roleEntity -> {
            List<PermissionEntity> permissionEntities = roleEntity.permissionEntities;
            if (permissionEntities == null) return false;
            return permissionEntities.parallelStream().anyMatch(permissionEntity -> {
                return permissionEntity.routeId == routeEntity.id;
            });
        }).collect(Collectors.toList());
        SearchLocationEntity searchGroupEntity = new SearchLocationEntity();
        List<LocationEntity> groupEntities = locationService.get(searchGroupEntity);
        List<RoleEntity> finalRoleEntities = roleEntities;
        boolean check = groupEntities.parallelStream().anyMatch(groupEntity -> {
            if (groupEntity.managerEmloyeeId == employeeEntity.id) {
                return finalRoleEntities.parallelStream().anyMatch(roleEntity -> {
                    return roleEntity.id == 3 * groupEntity.id - 2;
                });
            }
            return false;
        });
        if (check) return;
        SearchTeamEntity searchTeamEntity = new SearchTeamEntity();
        List<TeamEntity> teamEntities = teamService.get(searchTeamEntity);
        check = teamEntities.parallelStream().anyMatch(teamEntity -> {
            if (teamEntity.leaderEmployeeId == employeeEntity.id) {
                return finalRoleEntities.parallelStream().anyMatch(roleEntity -> {
                    return roleEntity.id == 3 * teamEntity.locationId - 1;
                });
            }
            return false;
        });
        if (check) return;
        check = finalRoleEntities.parallelStream().anyMatch(roleEntity -> {
            return roleEntity.id == 3 * employeeEntity.locationId;
        });
        if (check) return;
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Không có quyền truy cập").build());
        return;
//        employeeEntity.groupEntity

        // For any pther methods besides login, the authToken must be verified
//        if (!path.startsWith("/demo-business-resource/login/")) {
//            String authToken = requestCtx.getHeaderString(DemoHTTPHeaderNames.AUTH_TOKEN);
//
//            // if it isn't valid, just kick them out.
//            if (!demoAuthenticator.isAuthTokenValid(serviceKey, authToken)) {
//                requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//            }
//        }
    }

    /**
     * Perform authorization based on roles.
     *
     * @param rolesAllowed
     * @param requestContext
     */
    private void performAuthorization(String[] rolesAllowed,
                                      ContainerRequestContext requestContext) throws AccessDeniedException {

        if (rolesAllowed.length > 0 && !isAuthenticated(requestContext)) {
            refuseRequest();
        }

        for (final String role : rolesAllowed) {
            if (requestContext.getSecurityContext().isUserInRole(role)) {
                return;
            }
        }

        refuseRequest();
    }

    /**
     * Check if the user is authenticated.
     *
     * @param requestContext
     * @return
     */
    private boolean isAuthenticated(final ContainerRequestContext requestContext) {
        // Return true if the user is authenticated or false otherwise
        // An implementation could be like:
        // return requestContext.getSecurityContext().getUserPrincipal() != null;
        return true;
    }

    /**
     * Refuse the request.
     */
    private void refuseRequest() throws AccessDeniedException {
        throw new AccessDeniedException(
                "You don't have permissions to perform this action.");
    }

    private String parseLink(String link) {
        List<String> linkParts = Arrays.stream(link.split("/")).filter(x -> !isNullOrEmpty(x)).collect(Collectors.toList());
        for (int i = 0; i < linkParts.size(); i++) {
            if (StringUtils.isStrictlyNumeric(linkParts.get(i)))
                linkParts.set(i, "*");
        }
        return String.join("/", linkParts);
    }
}