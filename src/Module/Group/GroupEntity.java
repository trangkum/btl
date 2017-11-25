package Module.Group;

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
public class GroupEntity implements Serializable {
    public int id;
    public String name;
    public int managerEmloyeeId;
    public EmployeeEntity managerEmployeeEntity;
    public List<EmployeeEntity> employeeEntities;
    public List<TeamEntity> teamEntities;
    public List<TicketEntity> ticketEntities;


    public GroupEntity() {
    }

    public GroupEntity(GroupModel GroupModel, Object... objects) {
        this.id = GroupModel.getId();
        this.name = GroupModel.getName();
        this.managerEmloyeeId = GroupModel.getManagerEmloyeeId();
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

    public GroupModel toModel() {
        GroupModel GroupModel = new GroupModel();
        GroupModel.setId(id);
        GroupModel.setName(name);
        GroupModel.setManagerEmloyeeId(managerEmloyeeId);
        return GroupModel;
    }
}
