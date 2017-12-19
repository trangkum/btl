package Module.Ticket;

import Module.Employee.EmployeeModel;
import Module.Location.LocationModel;
import Module.TicketRelater.TicketrelaterModel;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;
import java.util.Collection;

@StaticMetamodel(TicketModel.class)
public class TicketModel_ {
    public static volatile SingularAttribute<TicketModel, Integer> id;
    public static volatile SingularAttribute<TicketModel, String> content;
    public static volatile SingularAttribute<TicketModel, String> subject;
    public static volatile SingularAttribute<TicketModel, Integer> createEmployeeId;
    public static volatile SingularAttribute<TicketModel, Integer> status;
    public static volatile SingularAttribute<TicketModel, Integer> priority;
    public static volatile SingularAttribute<TicketModel, Timestamp> deadline;
    public static volatile SingularAttribute<TicketModel, Integer> assignedEmployeeId;
    public static volatile SingularAttribute<TicketModel, Byte> rating;
    public static volatile SingularAttribute<TicketModel, Integer> locationId;
    public static volatile SingularAttribute<TicketModel, Timestamp> resolvedTime;
    public static volatile SingularAttribute<TicketModel, Timestamp> closedTime;
    public static volatile SingularAttribute<TicketModel, Timestamp> createdTime;
    public static volatile SingularAttribute<TicketModel, Timestamp> updatedTime;
    public static volatile SingularAttribute<TicketModel, Timestamp> deletedTime;
    public static volatile SingularAttribute<TicketModel, EmployeeModel> employeeByCreateEmployeeId;
    public static volatile SingularAttribute<TicketModel, EmployeeModel> employeeByAssignedEmployeeId;
    public static volatile SingularAttribute<TicketModel, LocationModel> locationByLocationId;
    public static volatile CollectionAttribute<TicketModel, TicketrelaterModel> ticketrelatersById;


}
