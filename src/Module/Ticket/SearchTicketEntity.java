package Module.Ticket;

import AppStart.RESTTimestamp;
import Module.Employee.EmployeeModel;
import Module.Employee.EmployeeModel_;
import Module.FilterEntity;
import Module.Location.LocationModel;
import Module.Location.LocationModel_;

import javax.persistence.criteria.*;
import javax.ws.rs.QueryParam;
import java.util.List;

public class SearchTicketEntity extends FilterEntity {
    @QueryParam("id")
    public Integer id;
    @QueryParam("content")
    public String content;
    @QueryParam("subject")
    public String subject;

    @QueryParam("status")
    public Integer status;
    @QueryParam("priority")
    public Integer priority;
    @QueryParam("deadline")
    public String deadline;
    @QueryParam("rating")
    public Byte rating;
    @QueryParam("locationId")
    public Integer locationId;
    @QueryParam("resolvedTime")
    public String resolvedTime;
    @QueryParam("closedTime")
    public String closedTime;
    @QueryParam("createdTime")
    public String createdTime;
    @QueryParam("updatedTime")
    public String updatedTime;
    @QueryParam("deletedTime")
    public String deletedTime;
    @QueryParam("assignedEmployeeName")
    public String assignedEmployeeName;
    @QueryParam("createEmployeeName")
    public String createEmployeeName;

    public List<Predicate> applyTo(CriteriaBuilder builder, List<Predicate> predicates, Root<TicketModel> root) {

        if (id != null) {
            predicates.add(builder.equal(root.get(TicketModel_.id), id));
        }
        if (content != null && !content.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketModel_.content)), "%" + content.toLowerCase() + "%"));
        }
        if (subject != null && !subject.isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get(TicketModel_.subject)), "%" + subject.toLowerCase() + "%"));
        }
        if (status != null) {
            predicates.add(builder.equal(root.get(TicketModel_.status), status));
        }
        if (priority != null) {
            predicates.add(builder.equal(root.get(TicketModel_.priority), priority));
        }
        if (deadline != null && !deadline.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.deadline), RESTTimestamp.Parse(deadline)));
        }
        if (rating != null) {
            predicates.add(builder.equal(root.get(TicketModel_.rating), rating));
        }
        if (locationId != null) {
            predicates.add(builder.equal(root.get(TicketModel_.locationId), locationId));
        }
        if (resolvedTime != null && !resolvedTime.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.resolvedTime), resolvedTime));
        }
        if (closedTime != null && !closedTime.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.closedTime), closedTime));
        }
        if (createdTime != null && !createdTime.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.createdTime), createdTime));
        }
        if (updatedTime != null && !updatedTime.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.updatedTime), updatedTime));
        }
        if (deletedTime != null && !deletedTime.isEmpty()) {
            predicates.add(builder.equal(root.get(TicketModel_.deletedTime), deletedTime));
        }
        if (createEmployeeName != null && !createEmployeeName.isEmpty()) {
            Join<TicketModel, EmployeeModel> createEmployee = root.join(TicketModel_.employeeByCreateEmployeeId);
            predicates.add(builder.like(builder.lower(createEmployee.get(EmployeeModel_.name)), "%" + createEmployeeName.toLowerCase() + "%"));
        }
        if (assignedEmployeeName != null && !assignedEmployeeName.isEmpty()) {
            Join<TicketModel, EmployeeModel> assignedEmployee = root.join(TicketModel_.employeeByAssignedEmployeeId);
            predicates.add(builder.like(builder.lower(assignedEmployee.get(EmployeeModel_.name)), "%" + assignedEmployeeName.toLowerCase() + "%"));
        }
        return predicates;
    }

    @Override
    public boolean specOrder(CriteriaBuilder builder, CriteriaQuery criteria, Root root) {
        if ("createEmployeeName".equals(orderBy)) {
            Join<TicketModel, EmployeeModel> createEmployee = root.join(TicketModel_.employeeByCreateEmployeeId);
            if (orderType == null) orderType = "Asc";
            switch (orderType) {
                case "Asc":
                case "asc":
                    criteria.orderBy(builder.asc(createEmployee.get(EmployeeModel_.name)));
                    break;
                case "Desc":
                case "desc":
                    criteria.orderBy(builder.desc(createEmployee.get(EmployeeModel_.name)));
                    break;
            }
            return true;
        } else if ("assignedEmployeeName".equals(orderBy)) {
            Join<TicketModel, EmployeeModel> assignedEmployee = root.join(TicketModel_.employeeByAssignedEmployeeId);
            if (orderType == null) orderType = "Asc";
            switch (orderType) {
                case "Asc":
                case "asc":
                    criteria.orderBy(builder.asc(assignedEmployee.get(EmployeeModel_.name)));
                    break;
                case "Desc":
                case "desc":
                    criteria.orderBy(builder.desc(assignedEmployee.get(EmployeeModel_.name)));
                    break;
            }
            return true;
        }else if ("locationName".equals(orderBy)) {
            Join<TicketModel, LocationModel> locationModelJoin = root.join(TicketModel_.locationByLocationId);
            if (orderType == null) orderType = "Asc";
            switch (orderType) {
                case "Asc":
                case "asc":
                    criteria.orderBy(builder.asc(locationModelJoin.get(LocationModel_.name)));
                    break;
                case "Desc":
                case "desc":
                    criteria.orderBy(builder.desc(locationModelJoin.get(LocationModel_.name)));
                    break;
            }
            return true;
        }
        return false;
    }
}
