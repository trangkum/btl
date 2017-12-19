import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
// import {EdgeEntity} from "../edge/edge.Entity";
// import {ProblemEntity} from "../problem/problem.Entity";
import {TicketService} from "../Ticket/Ticket.Service";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {SearchTicketEntity} from "../Ticket/Ticket.SearchEntity";
import {ActivatedRoute} from "@angular/router";
import {ViewService} from "./View.Service";
import {PagingModel} from "../../Shared/MaterialComponent/paging/paging.model";
import {PriorityList, TicketStatus, TicketStatusList} from "../Enum/Enum";
import {AppComponent} from "../../app.component";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {TicketThreadService} from "../TicketThread/TicketThread.Service";
import {TicketReadService} from "../TicketRead/TicketRead.Service";
import {LocationService} from "../Location/Location.Service";
import {EmployeeEntity} from "../Employee/Employee.Entity";
import {SearchEmployeeEntity} from "../Employee/Employee.SearchEntity";
import {EmployeeService} from "../Employee/Employee.Service";

@Component({
    selector: 'App-MyRequest',
    templateUrl: './View.Component.html',
    styleUrls: ['./View.Component.css'],
    providers: [BottomToastsManager]
})
export class ViewComponent {
    ticketEntities: TicketEntity[] = [];
    searchTicketEntity: SearchTicketEntity = new SearchTicketEntity();
    title: string = "";
    urlId: string = "";
    urlType: string = "";
    PagingModel = new PagingModel(7, 10);
    PriorityList = PriorityList;
    TicketStatusList = TicketStatusList;
    ngbDateISOParserFormatter = new NgbDateISOParserFormatter();
    LocationList: any[] = [
        {name: "Chọn bộ phận IT", id: null}
    ];

    constructor(private employeeService: EmployeeService, private ticketReadService: TicketReadService, private locationService: LocationService, private ticketThreadService: TicketThreadService, private myRequestService: ViewService, private toastr: BottomToastsManager, private ticketService: TicketService, private route: ActivatedRoute, vcr: ViewContainerRef) {
        this.toastr.setRootViewContainerRef(vcr);
        this.route.params.subscribe(params => {
            this.urlId = params['urlId'];
            this.urlType = params['urlType'];
            switch (this.urlId) {
                case "MyRequest":
                    this.title = "Danh sách công việc yêu cầu";
                    this.myRequestService.action = "myRequests";
                    break;
                case "Related":
                    this.title = "Danh sách công việc liên quan";
                    this.myRequestService.action = "relateds";
                    break;
                case "MyWork":
                    this.title = "Danh sách công việc được giao";
                    this.myRequestService.action = "myWorks";
                    break;
                case "Team":
                    this.title = "Danh sách công việc của team";
                    this.myRequestService.action = "teams";
                    break;
                case "IT":
                    this.title = "Danh sách công việc của bộ phận IT";
                    this.myRequestService.action = "iTs";
                    this.locationService.Get().subscribe(locationList => {
                        locationList.forEach(location => {
                            this.LocationList.push({name: location.name, id: location.id});
                        });
                    });
                    break;
            }
            this.searchTicketEntity.status = this.getStatus(this.urlType);
            this.searchTicketEntity.orderBy = "createdTime";
            this.searchTicketEntity.orderType = "Desc";
            this.PagingModel = new PagingModel(7, 10);
            this.resetSearch();
            this.search();
        });
    }

    public getStatus(urlType: string): number {
        switch (urlType) {
            case "All":
                return null;
            default :
                return TicketStatus[urlType];
        }
    }

    public search() {
        this.searchTicketEntity.skip = this.PagingModel.take * this.PagingModel.Active;
        this.searchTicketEntity.take = this.PagingModel.take;
        this.myRequestService.Get(this.searchTicketEntity).subscribe(ticketEntites => {
            this.ticketEntities = ticketEntites;
            this.count();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    public resetSearch() {
        this.searchTicketEntity = new SearchTicketEntity();
        this.searchTicketEntity.status = this.getStatus(this.urlType);
        this.searchTicketEntity.orderBy = "createdTime";
        this.searchTicketEntity.orderType = "Desc";
        this.searchAssignedEmployee.name = "";
        this.searchCreateEmployee.name = "";
        this.deadline = "";
        this.search();
    }

    public toggleRead(ticket: TicketEntity) {
        ticket.ticketReadEntities[0].status = (ticket.ticketReadEntities[0].status + 1) % 2;
        this.ticketReadService.Update(ticket.ticketReadEntities[0]).subscribe(x => {
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    public count() {
        this.myRequestService.Count(this.searchTicketEntity).subscribe(data => {
            this.PagingModel.TotalPage = Math.ceil(data / this.PagingModel.take);
        });
    }

    public setOrderBy(orderBy: string) {
        if (this.searchTicketEntity.orderBy == orderBy) {
            if (this.searchTicketEntity.orderType == "Desc") {
                this.searchTicketEntity.orderType = "Asc";
            } else {
                this.searchTicketEntity.orderType = "Desc";
            }
        }
        else this.searchTicketEntity.orderType = "Asc";
        this.searchTicketEntity.orderBy = orderBy;
        this.search();
    }

    deadline: any;

    public fixNgbDate(ngb) {
        if (!ngb.isOpen()) {
            let t = document.getElementsByTagName("ngb-datepicker");
            for (let i = 0; i < t.length; i++) {
                t[i].parentNode.childNodes.forEach(y => {
                    if (y.nodeName == 'INPUT') {
                        (y as any).click();
                    }
                })
            }
            AppComponent.IsClickDatePicker = true;
            setTimeout((x) => {
                AppComponent.IsClickDatePicker = false
            }, 100);
        }
        ngb.toggle();
    }

    public setDeadline(event) {
        this.deadline = event;
        this.searchTicketEntity.deadline = this.ngbDateISOParserFormatter.format(this.deadline);
        this.search();
    }

    createEmployeeSearchList: Array<EmployeeEntity> = [];
    searchCreateEmployee: EmployeeEntity = new EmployeeEntity();

    ChooseCreateEmployeeSearch(employeeEntity: EmployeeEntity) {
        this.searchCreateEmployee = employeeEntity;
        this.searchTicketEntity.createEmployeeName = employeeEntity.name;
        this.search();
    }

    GetCreateEmployeeSearchList() {
        let b = new SearchEmployeeEntity();
        b.take = 1000;
        b.skip = 0;
        b.name = this.searchCreateEmployee.name;
        this.createEmployeeSearchList = null;
        this.ticketService.GetData("api/employees", b.ToParams()).subscribe(v => {
            this.createEmployeeSearchList = v.map((item: any) => {
                return new EmployeeEntity(item);
            });
            this.createEmployeeSearchList = this.createEmployeeSearchList.filter((v, i) => {
                return this.createEmployeeSearchList.findIndex(t => t.name == v.name) == i;
            });
            let temp = this.searchCreateEmployee.name;
            this.searchTicketEntity.createEmployeeId = null;
            this.searchCreateEmployee = new EmployeeEntity();
            this.searchCreateEmployee.name = temp;
        });
    }

    EnterCreateEmployee(event) {
        this.searchTicketEntity.createEmployeeName = event.target.value == null ? "" : event.target.value;
        this.search();
    }


    assignedEmployeeSearchList: Array<EmployeeEntity> = [];
    searchAssignedEmployee: EmployeeEntity = new EmployeeEntity();

    ChooseAssignedEmployeeSearch(employeeEntity: EmployeeEntity) {
        this.searchAssignedEmployee = employeeEntity;
        this.searchTicketEntity.assignedEmployeeName = employeeEntity.name;
        this.search();
    }

    GetAssignedEmployeeSearchList() {
        let b = new SearchEmployeeEntity();
        b.take = 1000;
        b.skip = 0;
        b.name = this.searchAssignedEmployee.name;
        this.assignedEmployeeSearchList = null;
        this.ticketService.GetData("api/employees", b.ToParams()).subscribe(v => {
            this.assignedEmployeeSearchList = v.map((item: any) => {
                return new EmployeeEntity(item);
            });
            this.assignedEmployeeSearchList = this.assignedEmployeeSearchList.filter((v, i) => {
                return this.assignedEmployeeSearchList.findIndex(t => t.name == v.name) == i;
            });
            let temp = this.searchAssignedEmployee.name;
            this.searchTicketEntity.assignedEmployeeId = null;
            this.searchAssignedEmployee = new EmployeeEntity();
            this.searchAssignedEmployee.name = temp;
        });
    }

    EnterAssignedEmployee(event) {
        this.searchTicketEntity.assignedEmployeeName = event.target.value == null ? "" : event.target.value;
        this.search();
    }
}