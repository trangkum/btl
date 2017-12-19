import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
// import {EdgeEntity} from "../edge/edge.Entity";
// import {ProblemEntity} from "../problem/problem.Entity";
import {TicketService} from "../Ticket/Ticket.Service";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {ActivatedRoute} from "@angular/router";
import {DataEntity} from "../../Shared/MaterialComponent/inputfile/Data.Entity";
import {AppComponent} from "../../app.component";
import {LocationService} from "../Location/Location.Service";
import {EmployeeEntity} from "../Employee/Employee.Entity";
import {EmployeeService} from "../Employee/Employee.Service";
import {SearchEmployeeEntity} from "../Employee/Employee.SearchEntity";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {TicketRelaterEntity} from "../TicketRelater/TicketRelater.Entity";
import {InputDiscussionComponent} from "../../Shared/MaterialComponent/InputDiscussion/InputDiscussion.component";
import {FileService} from "../File/File.Service";
import {PriorityList, TicketStatusList} from "../Enum/Enum";
import {TicketThreadEntity} from "../TicketThread/TicketThread.Entity";

@Component({
    selector: 'App-Comment',
    templateUrl: './Comment.Component.html',
    styleUrls: ['./Comment.Component.css'],
    providers: [BottomToastsManager]
})
export class CommentComponent {
    ticketEntity: TicketEntity = new TicketEntity();
    file: DataEntity = new DataEntity();
    PriorityList = PriorityList;
    LocationList: any[] = [
        {name: "Chọn bộ phận IT", id: null}
    ];
    CCName: string = "";
    CCList: EmployeeEntity[] = [];
    employeeList: EmployeeEntity[] = [];
    ngbDateISOParserFormatter = new NgbDateISOParserFormatter();
    deadline: NgbDateStruct;
    inputDiscussionComponent: InputDiscussionComponent = new InputDiscussionComponent();
    TicketStatusList = TicketStatusList;
    ticketThreadEntity: TicketThreadEntity = new TicketThreadEntity();

    constructor(private toastr: BottomToastsManager, private fileService: FileService, private employeeService: EmployeeService, private locationService: LocationService, private ticketService: TicketService, private route: ActivatedRoute, vcr: ViewContainerRef) {
        this.toastr.setRootViewContainerRef(vcr);
        this.route.params.subscribe(params => {
            let ticketId = params['ticketId'];
            this.ticketService.GetId(ticketId).subscribe(ticket => {
                this.ticketEntity = ticket;
                if (this.ticketEntity.ticketRelaterEntities != null) {
                    this.ticketEntity.ticketRelaterEntities.forEach(value => {
                        value.employeeEntity.IsSelected = true;
                        this.CCList.push(value.employeeEntity);
                    });
                } else {
                    this.ticketEntity.ticketRelaterEntities = [];
                }
                this.deadline = this.ngbDateISOParserFormatter.parse(this.ticketEntity.deadline);
            });
        });
        this.locationService.Get().subscribe(locationList => {
            locationList.forEach(location => {
                this.LocationList.push({name: location.name, id: location.id});
            });
        });
    }

    public saveChange() {
        this.ticketEntity.deadline = this.ngbDateISOParserFormatter.format(this.deadline);
        this.ticketEntity.ticketRelaterEntities = [];
        for (let i = 0, size = this.CCList.length;i<size;i++ ) {
            if (this.CCList[i].IsSelected) {
                let tr = new TicketRelaterEntity();
                tr.employeeId = this.CCList[i].id;
                this.ticketEntity.ticketRelaterEntities.push(tr);
            }
        }
        this.ticketService.Update(this.ticketEntity).subscribe(ticketEntity => {
            console.log(ticketEntity);
            this.toastr.ShowSuccess();
        }, e => {
            this.toastr.ShowError(e);
        });
    }

    public searchCC(CCName) {
        let employeeEntities: EmployeeEntity[] = [];
        this.CCName = CCName;
        for (let i of this.CCList) {
            if (i.IsSelected == true) {
                employeeEntities.push(i);
            }
        }
        let t = new SearchEmployeeEntity();
        t.briefName = CCName;
        this.employeeService.Get(t).subscribe(employeeList => {
            employeeList = employeeList.filter(employee => {
                return !employeeEntities.some(value2 => {
                    return employee.id == value2.id;
                })
            });
            employeeEntities.forEach(value => {
                employeeList.push(value);
            });
            this.CCList = employeeList;
        });
    }

    public fixNgbDate(ngb) {
        if (!ngb.isOpen()) {
            let t = document.getElementsByTagName("ngb-datepicker");
            console.log(t);
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

    public searchEmployeeList(search) {
        let t = new SearchEmployeeEntity();
        t.briefName = search.SearchData;
        this.employeeService.Get(t).subscribe(employeeList => {
            this.employeeList = employeeList;
        });
    }


    assignedEmployeeSearchList: Array<EmployeeEntity> = [];

    ChooseAssignedEmployeeSearch(employeeEntity: EmployeeEntity) {
        this.ticketEntity.assignedEmployeeEntity = employeeEntity;
        this.ticketEntity.assignedEmployeeId = employeeEntity.id;
        this.ticketEntity.IsEdit = true;
        // this.searchTicketEntity.assignedEmployeeName = employeeEntity.name;
        // this.search();
    }

    GetAssignedEmployeeSearchList() {
        let b = new SearchEmployeeEntity();
        b.take = 1000;
        b.skip = 0;
        b.name = this.ticketEntity.assignedEmployeeEntity.name;
        this.assignedEmployeeSearchList = null;
        this.ticketService.GetData("api/employees", b.ToParams()).subscribe(v => {
            this.assignedEmployeeSearchList = v.map((item: any) => {
                return new EmployeeEntity(item);
            });
            this.assignedEmployeeSearchList = this.assignedEmployeeSearchList.filter((v, i) => {
                return this.assignedEmployeeSearchList.findIndex(t => t.name == v.name) == i;
            });
            let temp = this.ticketEntity.assignedEmployeeEntity.name;
            this.ticketEntity.assignedEmployeeId = null;
            this.ticketEntity.assignedEmployeeEntity = new EmployeeEntity();
            this.ticketEntity.assignedEmployeeEntity.name = temp;
            this.ticketEntity.IsEdit = true;
        });
    }

    sendComment() {
        this.ticketThreadEntity.content = document.getElementById(this.inputDiscussionComponent.Id).innerHTML;
        this.ticketService.AddComment(this.ticketEntity.id, this.ticketThreadEntity).subscribe(data => {
            this.ticketEntity.ticketThreadEntities.push(data);
        });
    }

    goToReply(){
        document.getElementById(this.inputDiscussionComponent.Id).scrollIntoView();
        return false;
    }
}