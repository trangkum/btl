package AppStart;

import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.Employee.EmployeeService;
import Module.File.FileService;
import Module.Location.LocationService;
import Module.Permission.PermissionService;
import Module.Role.RoleService;
import Module.Route.RouteService;
import Module.Team.TeamService;
import Module.Ticket.TicketService;
import Module.TicketAttribute.TicketAttributeService;
import Module.TicketImage.TicketImageService;
import Module.TicketRead.TicketReadService;
import Module.TicketRelater.TicketRelaterService;
import Module.TicketThread.TicketThreadService;
import Module.User.TokenService;
import Module.User.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(new DatabaseService()).to(IDatabaseService.class);
        bind(new DatabaseControllService()).to(IDatabaseControllService.class);
        bind(new EmployeeService()).to(EmployeeService.class);
        bind(new FileService()).to(FileService.class);
        bind(new LocationService()).to(LocationService.class);
        bind(new PermissionService()).to(PermissionService.class);
        bind(new RoleService()).to(RoleService.class);
        bind(new RouteService()).to(RouteService.class);
        bind(new TeamService()).to(TeamService.class);
        bind(new TicketService()).to(TicketService.class);
        bind(new TicketAttributeService()).to(TicketAttributeService.class);
        bind(new TicketImageService()).to(TicketImageService.class);
        bind(new TicketReadService()).to(TicketReadService.class);
        bind(new TicketRelaterService()).to(TicketRelaterService.class);
        bind(new TicketThreadService()).to(TicketThreadService.class);
        bind(new UserService()).to(UserService.class);
        bind(new TokenService()).to(TokenService .class);
    }
}