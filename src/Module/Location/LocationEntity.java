package Module.Location;

import Module.Employee.EmployeeEntity;
import Module.Employee.EmployeeModel;
import Module.Team.TeamEntity;
import Module.Team.TeamModel;
import Module.Ticket.TicketEntity;
import Module.Ticket.TicketModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class LocationEntity implements Serializable {
    public int id;
    public String name;
    public int managerEmloyeeId;
    public EmployeeEntity managerEmployeeEntity;
    public List<EmployeeEntity> employeeEntities;
    public List<TeamEntity> teamEntities;
    public List<TicketEntity> ticketEntities;


    public LocationEntity() {
    }

    public LocationEntity(LocationModel LocationModel, Object... objects) {
        this.id = LocationModel.getId();
        this.name = LocationModel.getName();
        this.managerEmloyeeId = LocationModel.getManagerEmloyeeId();
        for (Object object : objects) {
            if (object instanceof EmployeeModel) {
                this.managerEmployeeEntity = new EmployeeEntity((EmployeeModel) object);
            } else if (object instanceof Collection) {
                for (Object o : (Collection<Object>) object) {
                    if (o instanceof EmployeeModel) {
                        this.employeeEntities = ((Collection<EmployeeModel>) object).parallelStream().map(EmployeeEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TeamModel) {
                        this.teamEntities = ((Collection<TeamModel>) object).parallelStream().map(TeamEntity::new).collect(Collectors.toList());
                        break;
                    } else if (o instanceof TicketModel) {
                        this.ticketEntities = ((Collection<TicketModel>) object).parallelStream().map(TicketEntity::new).collect(Collectors.toList());
                        break;
                    }
                }
            }
        }
    }

    public LocationModel toModel() {
        LocationModel LocationModel = new LocationModel();
        LocationModel.setId(id);
        LocationModel.setName(name);
        LocationModel.setManagerEmloyeeId(managerEmloyeeId);
        return LocationModel;
    }
}
