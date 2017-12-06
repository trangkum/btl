package Module.Team;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Location.LocationEntity;
import Module.Location.LocationModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class TeamEntity implements Serializable {
    public int id;
    public String name;
    public int locationId;
    public int leaderEmployeeId;
    public List<EmployeeEntity> employeeEntities;
    public LocationEntity locationEntity;
    public EmployeeEntity leaderEmployeeEntity;


    public TeamEntity() {
    }

    public TeamEntity(TeamModel TeamModel, Object... objects) {
        this.id = TeamModel.getId();
        this.name = TeamModel.getName();
        this.locationId = TeamModel.getLocationId();
        this.leaderEmployeeId = TeamModel.getLeaderEmployeeId();
        for (Object object : objects) {
            if (object instanceof LocationModel) {
                this.locationEntity = new LocationEntity((LocationModel) object);
            } else if (object instanceof EmployeeModel) {
                this.leaderEmployeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof EmployeeModel) {
                        this.employeeEntities = ((Collection<EmployeeModel>) object).parallelStream().map(EmployeeEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public TeamModel toModel() {
        TeamModel TeamModel = new TeamModel();
        TeamModel.setId(id);
        TeamModel.setLocationId(locationId);
        TeamModel.setName(name);
        TeamModel.setLeaderEmployeeId(leaderEmployeeId);
        return TeamModel;
    }
}
