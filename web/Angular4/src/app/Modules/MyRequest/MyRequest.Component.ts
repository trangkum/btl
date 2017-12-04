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

@Component({
    selector: 'App-MyRequest',
    templateUrl: './MyRequest.Component.html',
    styleUrls: ['./MyRequest.Component.css'],
    providers: [BottomToastsManager]
})
export class MyRequestComponent {
    ticketEntities: TicketEntity[] = [];
    searchTicketEntity: SearchTicketEntity = new SearchTicketEntity();

    constructor(private toastr: BottomToastsManager, private ticketService: TicketService, vcr: ViewContainerRef) {
        // this.ticketService.Get(this.searchTicketEntity).subscribe(list => {
        //     this.ticketEntities = list;
        // });
        let x = {"subject": "aaaa", "createEmployeeId": 11, "priority": 3};
        let t = new TicketEntity(x);
        this.ticketEntities.push(t);
        // t.createEmployeeEntity.name
    }


}