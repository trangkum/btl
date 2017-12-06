package Module.Team;


import Module.Employee.EmployeeModel;
import Module.Location.LocationModel;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;

@Entity
@Table(name = "team", schema = "btl", catalog = "")
public class TeamModel {
    private Integer id;
    private String name;
    private Integer locationId;
    private Integer leaderEmployeeId;
    private Collection<EmployeeModel> employeesById;
    private LocationModel locationByLocationId;
    private EmployeeModel employeeByLeaderEmployeeId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "locationId", nullable = false)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "leaderEmployeeId", nullable = false)
    public Integer getLeaderEmployeeId() {
        return leaderEmployeeId;
    }

    public void setLeaderEmployeeId(Integer leaderEmployeeId) {
        this.leaderEmployeeId = leaderEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamModel teamModel = (TeamModel) o;

        if (id != null ? !id.equals(teamModel.id) : teamModel.id != null) return false;
        if (name != null ? !name.equals(teamModel.name) : teamModel.name != null) return false;
        if (locationId != null ? !locationId.equals(teamModel.locationId) : teamModel.locationId != null) return false;
        if (leaderEmployeeId != null ? !leaderEmployeeId.equals(teamModel.leaderEmployeeId) : teamModel.leaderEmployeeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (leaderEmployeeId != null ? leaderEmployeeId.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "teamByTeamId")
    public Collection<EmployeeModel> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeModel> employeesById) {
        this.employeesById = employeesById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public LocationModel getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationModel locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaderEmployeeId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeModel getEmployeeByLeaderEmployeeId() {
        return employeeByLeaderEmployeeId;
    }

    public void setEmployeeByLeaderEmployeeId(EmployeeModel employeeByLeaderEmployeeId) {
        this.employeeByLeaderEmployeeId = employeeByLeaderEmployeeId;
    }
}

@StaticMetamodel(TeamModel.class)
class TeamModel_ {
    public static volatile SingularAttribute<TeamModel, Integer> id;
    public static volatile SingularAttribute<TeamModel, String> name;
    public static volatile SingularAttribute<TeamModel, Integer> locationId;
    public static volatile SingularAttribute<TeamModel, Integer> leaderEmployeeId;
}