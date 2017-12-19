package Module.Auth;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeService;
import Module.Location.LocationEntity;
import Module.Location.LocationService;
import Module.Location.SearchLocationEntity;
import Module.Permission.PermissionEntity;
import Module.Permission.PermissionService;
import Module.Permission.SearchPermissionEntity;
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

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Path("/authentication")
public class AuthenticationEndpoint {
    @Inject
    private UserService userService;
    @Inject
    private TokenService tokenService;
    @Inject
    private RoleService roleService;
    @Inject
    private LocationService locationService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private TeamService teamService;
    @Inject
    private PermissionService permissionService;
    @Inject
    private RouteService routeService;
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUserJson(@QueryParam("originUrl") String originUrl, Credentials credentials) {
        try {
            // Authenticate the user using the credentials provided
            UserEntity userEntity = authenticate(credentials.getUsername(), credentials.getPassword());
            // Issue a token for the user
            String token = issueToken(userEntity);
            NewCookie cookie = new NewCookie("auth-tokenKey", token, "/", "", "comment", 3600 * 24 * 30, false);
            NewCookie cookie2 = null;
            if (!StringUtils.isNullOrEmpty(originUrl)) {
                String[] originStrings = originUrl.split("/");
                String origin = originStrings[0] + "//" + originStrings[2];
                cookie2 = new NewCookie("auth-tokenKey", token, "/", origin, "comment", 3600 * 24 * 30, false);
            }
            // Return the token on the response
            return Response.ok("{\"auth-tokenKey\":\"" + token + "\"}").cookie(cookie, cookie2).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tên đăng nhập hoặc mật khẩu không đúng").build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@QueryParam("originUrl") String originUrl, @FormParam("username") String username,
                                     @FormParam("password") String password) {
        try {
            // Authenticate the user using the credentials provided
            UserEntity userEntity = authenticate(username, password);
            // Issue a token for the user
            String token = issueToken(userEntity);
            NewCookie cookie = new NewCookie("auth-tokenKey", token, "/", "", "comment", 3600 * 24 * 30, false);
            NewCookie cookie2 = null;
            if (!StringUtils.isNullOrEmpty(originUrl) && originUrl != "null") {
                String[] originStrings = originUrl.split("/");
                String origin = originStrings[0] + "//" + originStrings[2];
                cookie2 = new NewCookie("auth-tokenKey", token, "/", origin, "comment", 3600 * 24 * 30, false);
            }
            return Response.ok("{\"auth-tokenKey\":\"" + token + "\"}").cookie(cookie).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Tên đăng nhập hoặc mật khẩu không đúng").build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Origin Url không đúng").build();
        }
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@CookieParam("auth-tokenKey") Cookie cookie) throws URISyntaxException {
        if (cookie != null) {
            TokenEntity tokenEntity = tokenService.getByTokenKey(cookie.getValue());
            if (tokenEntity != null) tokenService.delete(tokenEntity.id);
            NewCookie newCookie = new NewCookie("auth-tokenKey", "", "/", "", "comment", 0, false);
            return Response.temporaryRedirect(new URI("/Login.html?isLogOut=true")).cookie(newCookie).build();
        }
        return Response.temporaryRedirect(new URI("/Login.html?isLogOut=true")).build();
    }

    @GET
    @Path("/logout/{auth-tokenKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("auth-tokenKey") String tokenKey) {
        if (tokenKey != null) {
            TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey);
            if (tokenEntity != null) tokenService.delete(tokenEntity.id);
            NewCookie newCookie = new NewCookie("auth-tokenKey", "", "/", "", "comment", 0, false);
            return Response.ok("OK").cookie(newCookie).build();
        }
        return Response.ok("OK - No session").build();
    }

    @GET
    @Path("/routeInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RouteEntity> userInfo(@CookieParam("auth-tokenKey") Cookie cookieTokenKey) {
        if (cookieTokenKey != null) {
            String tokenKey = cookieTokenKey.getValue();
            if (tokenKey == null || tokenKey.trim().equals("")) {
                return null;
            }
            TokenEntity tokenEntity = tokenService.getByTokenKey(tokenKey);
            if (tokenEntity == null) {
                return null;
            }
            UserEntity userEntity = userService.get(tokenEntity.userId);
            if (userEntity == null) {
                return null;
            }
            EmployeeEntity employeeEntity = employeeService.get(userEntity.employeeId);
            if (employeeEntity == null) {
                return null;
            }
            SearchRoleEntity searchRoleEntity = new SearchRoleEntity();
            SearchLocationEntity searchGroupEntity = new SearchLocationEntity();
            searchGroupEntity.managerEmployeeId = employeeEntity.id;
            List<LocationEntity> groupEntities = locationService.get(searchGroupEntity);
            List<RoleEntity> roleEntities = roleService.get(searchRoleEntity);
            List<RoleEntity> roleEntitiesOfCurrentUser = new ArrayList<>();
            roleEntitiesOfCurrentUser.addAll(groupEntities.parallelStream().map(locationEntity -> {
                return roleEntities.parallelStream().filter(roleEntity -> {
                    return roleEntity.id == 3 * locationEntity.id - 2;
                }).findAny().get();
            }).collect(Collectors.toList()));
            SearchTeamEntity searchTeamEntity = new SearchTeamEntity();
            searchTeamEntity.leaderEmployeeId = employeeEntity.id;
            List<TeamEntity> teamEntities = teamService.get(searchTeamEntity);
            roleEntitiesOfCurrentUser.addAll(teamEntities.parallelStream().map(teamEntity -> {
                return roleEntities.parallelStream().filter(roleEntity -> {
                    return roleEntity.id == 3 * teamEntity.id - 1;
                }).findAny().get();
            }).collect(Collectors.toList()));
            roleEntitiesOfCurrentUser.add(roleEntities.parallelStream().filter(roleEntity -> {
                return roleEntity.id == 3 * employeeEntity.locationId;
            }).findAny().get());
            SearchPermissionEntity searchPermissionEntity = new SearchPermissionEntity();
            List<PermissionEntity> permissionEntities = permissionService.get(searchPermissionEntity);
            List<RouteEntity> routeEntities = permissionEntities.parallelStream().filter(permissionEntity -> {
                return roleEntitiesOfCurrentUser.parallelStream().anyMatch(roleEntity -> {
                    return roleEntity.id == permissionEntity.roleId;
                });
            }).map(permissionEntity -> permissionEntity.routeId).distinct().map(integer -> {
                return routeService.get(integer);
            }).collect(Collectors.toList());
            return routeEntities;
        }
        return null;
    }

    private UserEntity authenticate(String username, String password) throws BadRequestException {
        UserEntity userEntity = userService.getByUserName(username);
        if (userEntity == null || !userEntity.passWord.equals(password))
            throw new BadRequestException("Tên đăng nhập hoặc mật khẩu không đúng");
        else return userEntity;
    }

    private String issueToken(UserEntity userEntity) {
        Random random = new SecureRandom();
        String tokenKey = new BigInteger(130, random).toString(32);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.userId = userEntity.id;
        tokenEntity.tokenKey = tokenKey;
        tokenEntity.expriedTime = Timestamp.valueOf(LocalDateTime.now().plusMonths(1)).toString();
        tokenService.create(tokenEntity);
        return tokenKey;
    }
}
