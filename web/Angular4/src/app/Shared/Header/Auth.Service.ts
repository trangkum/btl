import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {Injectable} from '@angular/core';
import {HOSTNAME} from "../../app.module";
import {HttpService} from "../HttpService";
import {RouteEntity} from "../../Modules/Route/Route.Entity";

@Injectable()
export class AuthService {
    public url: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME + "api/authentication";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(HOSTNAME + url, {params: data}, false)
        .map(res => {
            return res.json();
        });
    }

    GetRouteInfo(): Observable<RouteEntity[]> {
        return this.Http.get(this.url + "/routeInfo")
        .map(res => {
            return res.json().map((item: any) => {
                return new RouteEntity(item);
            });
        });
    }


}