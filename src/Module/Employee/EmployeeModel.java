package Module.Employee;

import Module.File.FileModel;
import Module.Location.LocationModel;
import Module.Team.TeamModel;
import Module.Ticket.TicketModel;
import Module.TicketRead.TicketreadModel;
import Module.TicketRelater.TicketrelaterModel;
import Module.TicketThread.TicketthreadModel;
import Module.User.UserModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "btl", catalog = "")
public class EmployeeModel {
    private Integer id;
    private String email;
    private String name;
    private Integer teamId;
    private Integer locationId;
    private String briefName;
    private TeamModel teamByTeamId;
    private LocationModel locationByLocationId;
    private Collection<FileModel> filesById;
    private Collection<LocationModel> locationsById;
    private Collection<TeamModel> teamsById;
    private Collection<TicketModel> ticketsById;
    private Collection<TicketModel> ticketsById_0;
    private Collection<TicketreadModel> ticketreadsById;
    private Collection<TicketrelaterModel> ticketrelatersById;
    private Collection<TicketthreadModel> ticketthreadsById;
    private Collection<UserModel> usersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "teamId", nullable = true)
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "locationId", nullable = true)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "briefName", nullable = true, length = 45)
    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeModel that = (EmployeeModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (briefName != null ? !briefName.equals(that.briefName) : that.briefName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (briefName != null ? briefName.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId", referencedColumnName = "id", insertable = false, updatable = false)
    public TeamModel getTeamByTeamId() {
        return teamByTeamId;
    }

    public void setTeamByTeamId(TeamModel teamByTeamId) {
        this.teamByTeamId = teamByTeamId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId", referencedColumnName = "id", insertable = false, updatable = false)
    public LocationModel getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationModel locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByEmployeeId")
    public Collection<FileModel> getFilesById() {
        return filesById;
    }

    public void setFilesById(Collection<FileModel> filesById) {
        this.filesById = filesById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByManagerEmloyeeId")
    public Collection<LocationModel> getLocationsById() {
        return locationsById;
    }

    public void setLocationsById(Collection<LocationModel> locationsById) {
        this.locationsById = locationsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByLeaderEmployeeId")
    public Collection<TeamModel> getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(Collection<TeamModel> teamsById) {
        this.teamsById = teamsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByCreateEmployeeId")
    public Collection<TicketModel> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<TicketModel> ticketsById) {
        this.ticketsById = ticketsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByAssignedEmployeeId")
    public Collection<TicketModel> getTicketsById_0() {
        return ticketsById_0;
    }

    public void setTicketsById_0(Collection<TicketModel> ticketsById_0) {
        this.ticketsById_0 = ticketsById_0;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByEmployeeId")
    public Collection<TicketreadModel> getTicketreadsById() {
        return ticketreadsById;
    }

    public void setTicketreadsById(Collection<TicketreadModel> ticketreadsById) {
        this.ticketreadsById = ticketreadsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByEmployeeId")
    public Collection<TicketrelaterModel> getTicketrelatersById() {
        return ticketrelatersById;
    }

    public void setTicketrelatersById(Collection<TicketrelaterModel> ticketrelatersById) {
        this.ticketrelatersById = ticketrelatersById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByEmployeeId")
    public Collection<TicketthreadModel> getTicketthreadsById() {
        return ticketthreadsById;
    }

    public void setTicketthreadsById(Collection<TicketthreadModel> ticketthreadsById) {
        this.ticketthreadsById = ticketthreadsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employeeByEmployeeId")
    public Collection<UserModel> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UserModel> usersById) {
        this.usersById = usersById;
    }
}

