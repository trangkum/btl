package Module.Group;


import Module.Employee.EmployeeModel;
import Module.Team.TeamModel;
import Module.Ticket.TicketModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "group", schema = "btl", catalog = "")
public class GroupModel {
    private Integer id;
    private String name;
    private Integer managerEmloyeeId;
    private Collection<EmployeeModel> employeesById;
    private EmployeeModel employeeByManagerEmloyeeId;
    private Collection<TeamModel> teamsById;
    private Collection<TicketModel> ticketsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Integer getManagerEmloyeeId() {
        return managerEmloyeeId;
    }

    public void setManagerEmloyeeId(Integer managerEmloyeeId) {
        this.managerEmloyeeId = managerEmloyeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupModel that = (GroupModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (managerEmloyeeId != null ? !managerEmloyeeId.equals(that.managerEmloyeeId) : that.managerEmloyeeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (managerEmloyeeId != null ? managerEmloyeeId.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupByGroupId")
    public Collection<EmployeeModel> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeModel> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerEmloyeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByManagerEmloyeeId() {
        return employeeByManagerEmloyeeId;
    }

    public void setEmployeeByManagerEmloyeeId(EmployeeModel employeeByManagerEmloyeeId) {
        this.employeeByManagerEmloyeeId = employeeByManagerEmloyeeId;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupByGroupId")
    public Collection<TeamModel> getTeamsById() {
        return teamsById;
    }

    public void setTeamsById(Collection<TeamModel> teamsById) {
        this.teamsById = teamsById;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupByGroupId")
    public Collection<TicketModel> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<TicketModel> ticketsById) {
        this.ticketsById = ticketsById;
    }
}

@StaticMetamodel(GroupModel.class)
class GroupModel_ {
    public static volatile SingularAttribute<GroupModel, Integer> id;
    public static volatile SingularAttribute<GroupModel, String> name;
    public static volatile SingularAttribute<GroupModel, Integer> managerEmloyeeId;
}
