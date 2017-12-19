import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {TicketReadEntity} from "./TicketRead.Entity";
import {HOSTNAME} from "../../app.module";

@Injectable()
export class TicketReadService {
    public url: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME+"api/ticketReads";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }


    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new TicketReadEntity(res.json());
            });
    }

    Create(data: any): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new TicketReadEntity(res.json());
            });
    }

    Update(data: any): Observable<any> {
        return this.Http.put(`${this.url}/${data.ticketId}`, data)
            .map(res => {
                return new TicketReadEntity(res.json());
            });
    }

    Delete(data: any): Observable<any> {
        return this.Http.delete(`${this.url}/${data.ticketId}`)
            .catch(e => Observable.throw(e));
    }

//		Getproblem(shapeId: string) {
//			return this.Http.get(`${this.url}/${shapeId}/problem`)
//				.map(res => {
//					return new problemEntity(res.json());
//				});
//		}
//		Addproblem(shapeId: string, problemId: string) {
//			return this.Http.post(`${this.url}/${shapeId}/problem/${problemId}`, {});
//		}
//		Updateproblem(shapeId: string, problemId: string) {
//			return this.Http.put(`${this.url}/${shapeId}/problem/${problemId}`, {});
//		}
//		Deleteproblem(shapeId: string, problemId: string) {
//			return this.Http.delete(`${this.url}/${shapeId}/problem/${problemId}`);
//		}
//		Getuser(shapeId: string) {
//			return this.Http.get(`${this.url}/${shapeId}/user`)
//				.map(res => {
//					return new userEntity(res.json());
//				});
//		}
//		Adduser(shapeId: string, userId: string) {
//			return this.Http.post(`${this.url}/${shapeId}/user/${userId}`, {});
//		}
//		Updateuser(shapeId: string, userId: string) {
//			return this.Http.put(`${this.url}/${shapeId}/user/${userId}`, {});
//		}
//		Deleteuser(shapeId: string, userId: string) {
//			return this.Http.delete(`${this.url}/${shapeId}/user/${userId}`);
//		}
//		Getedge(shapeId: string) {
//			return this.Http.get(`${this.url}/${shapeId}/edges`)
//				.map(res => {
//					return res.json().map(e => new edgeEntity(e));
//				});
//		}
//		Addedge(shapeId: string, edgeId: string) {
//			return this.Http.post(`${this.url}/${shapeId}/edges/${edgeId}`, {});
//		}
//		Updateedge(shapeId: string, edgeId: string) {
//			return this.Http.put(`${this.url}/${shapeId}/edges/${edgeId}`, {});
//		}
//		Deleteedge(shapeId: string, edgeId: string) {
//			return this.Http.delete(`${this.url}/${shapeId}/edges/${edgeId}`);
//		}
}