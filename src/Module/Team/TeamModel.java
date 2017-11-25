package Module.Team;

import Module.Employee.EmployeeModel;
import Module.Group.GroupModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team", schema = "btl", catalog = "")
public class TeamModel {
    private int id;
    private String name;
    private int groupId;
    private int leaderEmployeeId;
    private Collection<EmployeeModel> employeesById;
    private GroupModel groupByGroupId;
    private EmployeeModel employeeByLeaderEmployeeId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "groupId", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "leaderEmployeeId", nullable = false)
    public int getLeaderEmployeeId() {
        return leaderEmployeeId;
    }

    public void setLeaderEmployeeId(int leaderEmployeeId) {
        this.leaderEmployeeId = leaderEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamModel teamModel = (TeamModel) o;

        if (id != teamModel.id) return false;
        if (groupId != teamModel.groupId) return false;
        if (leaderEmployeeId != teamModel.leaderEmployeeId) return false;
        if (name != null ? !name.equals(teamModel.name) : teamModel.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + groupId;
        result = 31 * result + leaderEmployeeId;
        return result;
    }

    @OneToMany(mappedBy = "teamByTeamId")
    public Collection<EmployeeModel> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeModel> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne
    @JoinColumn(name = "groupId", referencedColumnName = "id", nullable = false)
    public GroupModel getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupModel groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "leaderEmployeeId", referencedColumnName = "id", nullable = false)
    public EmployeeModel getEmployeeByLeaderEmployeeId() {
        return employeeByLeaderEmployeeId;
    }

    public void setEmployeeByLeaderEmployeeId(EmployeeModel employeeByLeaderEmployeeId) {
        this.employeeByLeaderEmployeeId = employeeByLeaderEmployeeId;
    }
}
