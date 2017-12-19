import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {HttpService} from "../../Shared/HttpService";
import {Injectable} from '@angular/core';
import {HOSTNAME} from "../../app.module";
import {SearchLocationEntity} from "./Location.SearchEntity";
import {LocationEntity} from "./Location.Entity";

@Injectable()
export class LocationService {
    public url: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME + "api/locations";
    }

    GetData(url: string, data: any): Observable<any> {
        let http = <HttpService>this.Http;
        return http.get(url, {params: data}, false)
            .map(res => {
                return res.json();
            });
    }

    Get(Search?: SearchLocationEntity): Observable<LocationEntity[]> {
        return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
            .map(res => {
                return res.json().map((item: any) => {
                    return new LocationEntity(item);
                });
            });
    }

    Count(Search?: SearchLocationEntity): Observable<number> {
        Search = Search === undefined ? new SearchLocationEntity() : Search;
        return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
            .map(res => {
                return res.json();
            });
    }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new LocationEntity(res.json());
            });
    }

    Create(data: any): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new LocationEntity(res.json());
            });
    }

    Update(data: any): Observable<any> {
        return this.Http.put(`${this.url}/${data.Id}`, data)
            .map(res => {
                return new LocationEntity(res.json());
            });
    }

    Delete(data: any): Observable<any> {
        return this.Http.delete(`${this.url}/${data.Id}`)
            .catch(e => Observable.throw(e));
    }

//		Getproblem(locationId: string) {
//			return this.Http.get(`${this.url}/${locationId}/problem`)
//				.map(res => {
//					return new problemEntity(res.json());
//				});
//		}
//		Addproblem(locationId: string, problemId: string) {
//			return this.Http.post(`${this.url}/${locationId}/problem/${problemId}`, {});
//		}
//		Updateproblem(locationId: string, problemId: string) {
//			return this.Http.put(`${this.url}/${locationId}/problem/${problemId}`, {});
//		}
//		Deleteproblem(locationId: string, problemId: string) {
//			return this.Http.delete(`${this.url}/${locationId}/problem/${problemId}`);
//		}
//		Getuser(locationId: string) {
//			return this.Http.get(`${this.url}/${locationId}/user`)
//				.map(res => {
//					return new userEntity(res.json());
//				});
//		}
//		Adduser(locationId: string, userId: string) {
//			return this.Http.post(`${this.url}/${locationId}/user/${userId}`, {});
//		}
//		Updateuser(locationId: string, userId: string) {
//			return this.Http.put(`${this.url}/${locationId}/user/${userId}`, {});
//		}
//		Deleteuser(locationId: string, userId: string) {
//			return this.Http.delete(`${this.url}/${locationId}/user/${userId}`);
//		}
//		Getedge(locationId: string) {
//			return this.Http.get(`${this.url}/${locationId}/edges`)
//				.map(res => {
//					return res.json().map(e => new edgeEntity(e));
//				});
//		}
//		Addedge(locationId: string, edgeId: string) {
//			return this.Http.post(`${this.url}/${locationId}/edges/${edgeId}`, {});
//		}
//		Updateedge(locationId: string, edgeId: string) {
//			return this.Http.put(`${this.url}/${locationId}/edges/${edgeId}`, {});
//		}
//		Deleteedge(locationId: string, edgeId: string) {
//			return this.Http.delete(`${this.url}/${locationId}/edges/${edgeId}`);
//		}
}