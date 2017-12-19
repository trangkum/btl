import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {HOSTNAME} from "../../app.module";
import {SearchTicketEntity} from "../Ticket/Ticket.SearchEntity";
import {TicketEntity} from "../Ticket/Ticket.Entity";
import {CountUnreadEntity} from "./CountUnread.Entity";

@Injectable()
export class ViewService {
    public url: string;
    public action: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME + "api/view/";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchTicketEntity): Observable<TicketEntity[]> {
        return this.Http.get(this.url + this.action, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new TicketEntity(item);
                });
            });
    }

    Count(Search?: SearchTicketEntity): Observable<number> {
        Search = Search === undefined ? new SearchTicketEntity() : Search;
        return this.Http.get(this.url + this.action + "/count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    CountUnRead(action: string): Observable<CountUnreadEntity> {
        let http = <HttpService>this.Http;
        return http.get(this.url + action + "/countUnread", {}, false)
            .map(res => {
                return new CountUnreadEntity(res.json());
            });
    }
}