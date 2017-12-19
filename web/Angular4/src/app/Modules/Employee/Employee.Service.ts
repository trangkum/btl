import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {EmployeeEntity} from "./Employee.Entity";
import {SearchEmployeeEntity} from "./Employee.SearchEntity";
import {HOSTNAME} from "../../app.module";

@Injectable()
export class EmployeeService {
    public url: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME + "api/employees";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(HOSTNAME +url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchEmployeeEntity): Observable<any> {
        return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new EmployeeEntity(item);
                });
            });
    }

    Count(Search?: SearchEmployeeEntity): Observable<number> {
        Search = Search === undefined ? new SearchEmployeeEntity() : Search;
        return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new EmployeeEntity(res.json());
            });
    }

    Create(data: any): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new EmployeeEntity(res.json());
            });
    }

    Update(data: any): Observable<any> {
        return this.Http.put(`${this.url}/${data.Id}`, data)
            .map(res => {
                return new EmployeeEntity(res.json());
            });
    }

    Delete(data: any): Observable<any> {
        return this.Http.delete(`${this.url}/${data.Id}`)
            .catch(e => Observable.throw(e));
    }

//		Getproblem(employeeId: string) {
//			return this.Http.get(`${this.url}/${employeeId}/problem`)
//				.map(res => {
//					return new problemEntity(res.json());
//				});
//		}
//		Addproblem(employeeId: string, problemId: string) {
//			return this.Http.post(`${this.url}/${employeeId}/problem/${problemId}`, {});
//		}
//		Updateproblem(employeeId: string, problemId: string) {
//			return this.Http.put(`${this.url}/${employeeId}/problem/${problemId}`, {});
//		}
//		Deleteproblem(employeeId: string, problemId: string) {
//			return this.Http.delete(`${this.url}/${employeeId}/problem/${problemId}`);
//		}
//		Getuser(employeeId: string) {
//			return this.Http.get(`${this.url}/${employeeId}/user`)
//				.map(res => {
//					return new userEntity(res.json());
//				});
//		}
//		Adduser(employeeId: string, userId: string) {
//			return this.Http.post(`${this.url}/${employeeId}/user/${userId}`, {});
//		}
//		Updateuser(employeeId: string, userId: string) {
//			return this.Http.put(`${this.url}/${employeeId}/user/${userId}`, {});
//		}
//		Deleteuser(employeeId: string, userId: string) {
//			return this.Http.delete(`${this.url}/${employeeId}/user/${userId}`);
//		}
//		Getedge(employeeId: string) {
//			return this.Http.get(`${this.url}/${employeeId}/edges`)
//				.map(res => {
//					return res.json().map(e => new edgeEntity(e));
//				});
//		}
//		Addedge(employeeId: string, edgeId: string) {
//			return this.Http.post(`${this.url}/${employeeId}/edges/${edgeId}`, {});
//		}
//		Updateedge(employeeId: string, edgeId: string) {
//			return this.Http.put(`${this.url}/${employeeId}/edges/${edgeId}`, {});
//		}
//		Deleteedge(employeeId: string, edgeId: string) {
//			return this.Http.delete(`${this.url}/${employeeId}/edges/${edgeId}`);
//		}
}