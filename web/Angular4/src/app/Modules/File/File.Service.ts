import 'rxjs/Rx';
import {Observable} from 'rxjs/Rx';
import {Http} from '@angular/http';
import {Injectable} from '@angular/core';
import {FileEntity} from "./File.Entity";
import {HOSTNAME} from "../../app.module";

@Injectable()
export class FileService {
    public url: string;

    constructor(private Http: Http) {
        this.url = HOSTNAME + "api/files";
    }

    // Get(Search?: SearchFileEntity): Observable<any> {
    //     return this.Http.get(this.url, {params: Search === undefined ? null : Search.ToParams()})
    //         .map(res => {
    //             return res.json().map((item: any) => {
    //                 return new FileEntity(item);
    //             });
    //         });
    // }
    //
    // Count(Search?: SearchFileEntity): Observable<number> {
    //     Search = Search === undefined ? new SearchFileEntity() : Search;
    //     return this.Http.get(this.url + "/Count", {params: Search.ToParams()})
    //         .map(res => {
    //             return res.json();
    //         });
    // }

    GetId(Id: string): Observable<any> {
        return this.Http.get(`${this.url}/${Id}`)
            .map(res => {
                return new FileEntity(res.json());
            });
    }

    Create(data: any): Observable<any> {
        return this.Http.post(`${this.url}`, data)
            .map(res => {
                return new FileEntity(res.json());
            });
    }

    Update(data: any): Observable<any> {
        return this.Http.put(`${this.url}/${data.Id}`, data)
            .map(res => {
                return new FileEntity(res.json());
            });
    }

    Delete(data: any): Observable<any> {
        return this.Http.delete(`${this.url}/${data.Id}`)
            .catch(e => Observable.throw(e));
    }

    // GetUser(FileId: string) {
    //     return this.Http.get(`${this.url}/${FileId}/User`)
    //         .map(res => {
    //             return new UserEntity(res.json());
    //         });
    // }
    //
    // AddUser(FileId: string, UserId: string) {
    //     return this.Http.post(`${this.url}/${FileId}/User/${UserId}`, {});
    // }
    //
    // UpdateUser(FileId: string, UserId: string) {
    //     return this.Http.put(`${this.url}/${FileId}/User/${UserId}`, {});
    // }
    //
    // DeleteUser(FileId: string, UserId: string) {
    //     return this.Http.delete(`${this.url}/${FileId}/User/${UserId}`);
    // }

    // GetMasterContract(FileId: string) {
    //     return this.Http.get(`${this.url}/${FileId}/MasterContracts`)
    //         .map(res => {
    //             return res.json().map(e => new MasterContractEntity(e));
    //         });
    // }
    //
    // AddMasterContract(FileId: string, MasterContractId: string) {
    //     return this.Http.post(`${this.url}/${FileId}/MasterContracts/${MasterContractId}`, {});
    // }
    //
    // UpdateMasterContract(FileId: string, MasterContractId: string) {
    //     return this.Http.put(`${this.url}/${FileId}/MasterContracts/${MasterContractId}`, {});
    // }
    //
    // DeleteMasterContract(FileId: string, MasterContractId: string) {
    //     return this.Http.delete(`${this.url}/${FileId}/MasterContracts/${MasterContractId}`);
    // }
}