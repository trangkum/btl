package Module.Employee;

import Module.File.FileModel;
import Module.Group.GroupModel;
import Module.Team.TeamModel;
import Module.Ticket.TicketModel;
import Module.TicketRead.TicketreadModel;
import Module.TicketThread.TicketthreadModel;
import Module.TicketRelater.TicketrelaterModel;
import Module.User.UserModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "btl", catalog = "")
public class EmployeeModel {
    private int id;
    private String email;
    private String name;
    private int teamId;
    private int groupId;
    private String briefName;
    private TeamModel teamByTeamId;
    private GroupModel groupByGroupId;
    private Collection<FileModel> filesById;
    private Collection<GroupModel> groupsById;
    private Collection<TeamModel> teamsById;
    private Collection<TicketModel> ticketsById;
    private Collection<TicketModel> ticketsById_0;
    private Collection<TicketreadModel> ticketreadsById;
    private Collection<TicketrelaterModel> ticketrelatersById;
    private Collection<TicketthreadModel> ticketthreadsById;
    private Collection<UserModel> usersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "groupId", nullable = true)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

        if (id != that.id) return false;
        if (teamId != that.teamId) return false;
        if (groupId != that.groupId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (briefName != null ? !briefName.equals(that.briefName) : that.briefName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + teamId;
        result = 31 * result + groupId;
        result = 31 * result + (briefName != null ? briefName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    public TeamModel getTeamByTeamId() {
        return teamByTeamId;
    }

    public void setTeamByTeamId(TeamModel teamByTeamId) {
        this.teamByTeamId = teamByTeamId;
    }

    @ManyToOne
    @JoinColumn(name = "groupId", referencedColumnName = "id")
    public GroupModel getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupModel groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<FileModel> getFilesById() {
        return filesById;
    }

    public void setFilesById(Collection<FileModel> filesById) {
        this.filesById = filesById;
    }

    @OneToMany(mappedBy = "employeeByManagerEmloyeeId")
    public Collection<GroupModel> getGroupsById() {
        return groupsById;
    }

    public void setGroupsById(Collection<GroupModel> groupsById) {
        this.groupsById = groupsById;
    }

    @OneToMany(mappedBy = "employeeByLeaderEmployeeId")
    public Collection<TeamModel> getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(Collection<TeamModel> teamsById) {
        this.teamsById = teamsById;
    }

    @OneToMany(mappedBy = "employeeByCreateEmployeeId")
    public Collection<TicketModel> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<TicketModel> ticketsById) {
        this.ticketsById = ticketsById;
    }

    @OneToMany(mappedBy = "employeeByAssignedEmployeeId")
    public Collection<TicketModel> getTicketsById_0() {
        return ticketsById_0;
    }

    public void setTicketsById_0(Collection<TicketModel> ticketsById_0) {
        this.ticketsById_0 = ticketsById_0;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<TicketreadModel> getTicketreadsById() {
        return ticketreadsById;
    }

    public void setTicketreadsById(Collection<TicketreadModel> ticketreadsById) {
        this.ticketreadsById = ticketreadsById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<TicketrelaterModel> getTicketrelatersById() {
        return ticketrelatersById;
    }

    public void setTicketrelatersById(Collection<TicketrelaterModel> ticketrelatersById) {
        this.ticketrelatersById = ticketrelatersById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<TicketthreadModel> getTicketthreadsById() {
        return ticketthreadsById;
    }

    public void setTicketthreadsById(Collection<TicketthreadModel> ticketthreadsById) {
        this.ticketthreadsById = ticketthreadsById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<UserModel> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UserModel> usersById) {
        this.usersById = usersById;
    }
}
