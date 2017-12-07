import {Component, ViewContainerRef} from '@angular/core';
import {BottomToastsManager} from "../../Shared/CustomToaster";
import {PagingModel} from "app/Shared/MaterialComponent/paging/paging.model";
import {ModalComponent} from "../../Shared/MaterialComponent/modal/modal.component";
// import {EdgeEntity} from "../edge/edge.Entity";
import {UserEntity} from "../user/user.Entity";
// import {ProblemEntity} from "../problem/problem.Entity";
import {hitAreas, types} from "ngvas";
import {TicketService} from "../Ticket/Ticket.Service";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {SearchTicketEntity} from "../Ticket/Ticket.SearchEntity";
import {ActivatedRoute} from "@angular/router";
import {DataEntity} from "../../Shared/MaterialComponent/inputfile/Data.Entity";

@Component({
    selector: 'App-MyRequest',
    templateUrl: './CreateRequest.Component.html',
    styleUrls: ['./CreateRequest.Component.css'],
    providers: [BottomToastsManager]
})
export class CreateRequestComponent {
    ticketEntities: TicketEntity[] = [];
    searchTicketEntity: SearchTicketEntity = new SearchTicketEntity();
    title: string = "";
    urlId: string = "";
    file: DataEntity = new DataEntity();

    constructor(private toastr: BottomToastsManager, private ticketService: TicketService, private route: ActivatedRoute, vcr: ViewContainerRef) {
        this.route.params.subscribe(params => {
            this.urlId = params['urlId'];
            switch (this.urlId) {
                case "MyRequest":
                    this.title = "Danh sách công việc yêu cầu";
                    break;
                case "Related":
                    this.title = "Danh sách công việc liên quan";
                    break;
                case "MyWork":
                    this.title = "Danh sách công việc được giao";
                    break;
                case "Team":
                    this.title = "Danh sách công việc của team";
                    break;
                case "IT":
                    this.title = "Danh sách công việc của bộ phận IT";
                    break;
            }
        });
    }

    public showName(){

    }

}