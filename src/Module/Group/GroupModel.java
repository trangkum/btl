package Module.Group;

import Module.Team.TeamModel;
import Module.Ticket.TicketModel;
import Module.Employee.EmployeeModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "group", schema = "btl", catalog = "")
public class GroupModel {
    private int id;
    private String name;
    private int managerEmloyeeId;
    private Collection<EmployeeModel> employeesById;
    private EmployeeModel employeeByManagerEmloyeeId;
    private Collection<TeamModel> teamsById;
    private Collection<TicketModel> ticketsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "managerEmloyeeId", nullable = false)
    public int getManagerEmloyeeId() {
        return managerEmloyeeId;
    }

    public void setManagerEmloyeeId(int managerEmloyeeId) {
        this.managerEmloyeeId = managerEmloyeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupModel that = (GroupModel) o;

        if (id != that.id) return false;
        if (managerEmloyeeId != that.managerEmloyeeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + managerEmloyeeId;
        return result;
    }

    @OneToMany(mappedBy = "groupByGroupId")
    public Collection<EmployeeModel> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeModel> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne
    @JoinColumn(name = "managerEmloyeeId", referencedColumnName = "id", nullable = false)
    public EmployeeModel getEmployeeByManagerEmloyeeId() {
        return employeeByManagerEmloyeeId;
    }

    public void setEmployeeByManagerEmloyeeId(EmployeeModel employeeByManagerEmloyeeId) {
        this.employeeByManagerEmloyeeId = employeeByManagerEmloyeeId;
    }

    @OneToMany(mappedBy = "groupByGroupId")
    public Collection<TeamModel> getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(Collection<TeamModel> teamsById) {
        this.teamsById = teamsById;
    }

    @OneToMany(mappedBy = "groupByGroupId")
    public Collection<TicketModel> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<TicketModel> ticketsById) {
        this.ticketsById = ticketsById;
    }
}
