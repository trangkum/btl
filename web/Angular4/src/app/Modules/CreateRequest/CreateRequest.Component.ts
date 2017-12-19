import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
// import {EdgeEntity} from "../edge/edge.Entity";
// import {ProblemEntity} from "../problem/problem.Entity";
import {TicketService} from "../Ticket/Ticket.Service";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {ActivatedRoute} from "@angular/router";
import {DataEntity} from "../../Shared/MaterialComponent/inputfile/Data.Entity";
import {AppComponent} from "../../app.component";
import {PriorityList} from "../Enum/Enum";
import {LocationService} from "../Location/Location.Service";
import {EmployeeEntity} from "../Employee/Employee.Entity";
import {EmployeeService} from "../Employee/Employee.Service";
import {SearchEmployeeEntity} from "../Employee/Employee.SearchEntity";
import {NgbDateISOParserFormatter} from "@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {TicketRelaterEntity} from "../TicketRelater/TicketRelater.Entity";
import {InputDiscussionComponent} from "../../Shared/MaterialComponent/InputDiscussion/InputDiscussion.component";
import {FileService} from "../File/File.Service";
import {TicketImageEntity} from "../TicketImage/TicketImage.Entity";
import {InputfileComponent} from "../../Shared/MaterialComponent/inputfile/inputfile.component";

@Component({
    selector: 'App-MyRequest',
    templateUrl: './CreateRequest.Component.html',
    styleUrls: ['./CreateRequest.Component.css'],
    providers: [BottomToastsManager]
})
export class CreateRequestComponent {
    ticketEntity: TicketEntity = new TicketEntity();
    files: DataEntity[] = [];
    PriorityList = PriorityList;
    LocationList: any[] = [
        {name: "Chọn bộ phận IT", id: null}
    ];
    InputfileComponent = new InputfileComponent();
    ngbDateISOParserFormatter = new NgbDateISOParserFormatter();
    deadline: NgbDateStruct;
    inputDiscussionComponent: InputDiscussionComponent = new InputDiscussionComponent();
    employeeList: EmployeeEntity[] = [];
    CCName: string = "";
    CCList: EmployeeEntity[] = [];

    constructor(private toastr: BottomToastsManager, private fileService: FileService, private employeeService: EmployeeService, private locationService: LocationService, private ticketService: TicketService, private route: ActivatedRoute, vcr: ViewContainerRef) {
        this.locationService.Get().subscribe(locationList => {
            locationList.forEach(location => {
                this.LocationList.push({name: location.name, id: location.id});
            });
        });
        this.toastr.setRootViewContainerRef(vcr);
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


    public create() {
        this.ticketEntity.deadline = this.ngbDateISOParserFormatter.format(this.deadline);
        this.ticketEntity.ticketRelaterEntities = [];
        for (let i of this.CCList) {
            if (i.IsSelected) {
                let tr = new TicketRelaterEntity();
                tr.employeeId = i.id;
                this.ticketEntity.ticketRelaterEntities.push(tr);
            }
        }
        this.ticketEntity.content = document.getElementById(this.inputDiscussionComponent.Id).innerHTML;
        this.ticketEntity.ticketImageEntities = [];
        let isCreate = false;
        let files = this.files;
        this.files = [];
        let ticketEntity = new TicketEntity(this.ticketEntity);
        for (let i = 0, len = files.length; i < len; i++) {
            let file = files[i];
            if (file.length > 0) {
                this.fileService.Create(file).subscribe(fileEntity => {
                    let ti = new TicketImageEntity();
                    ti.fileId = fileEntity.id;
                    ticketEntity.ticketImageEntities.push(ti);
                });
            }
        }
        setInterval(v => {
            if (isCreate == false && ticketEntity.ticketImageEntities.length == files.length) {
                isCreate = true;
                this.ticketService.Create(ticketEntity).subscribe(ticket => {
                    console.log(ticket);
                    this.toastr.ShowSuccess();
                }, e => {
                    this.toastr.ShowError(e);
                });
            }
        }, 1000)

    }
}