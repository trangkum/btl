import {EmployeeEntity} from "../Employee/Employee.Entity";

export class UserEntity {

    userName: string;
    passWord: string;
    employeeId: number;
    employeeEntity : EmployeeEntity;
    
    IsEdit: boolean;
    IsActive: boolean = false;
    IsSelected: boolean = false;

    constructor(data: any = null) {
        if (data == null) {
            this.userName = null;
            this.passWord = null;
            this.employeeId = null;
            this.employeeEntity = new EmployeeEntity();
        } else {
            this.userName = data.userName;
            this.passWord = data.passWord;
            this.employeeId = data.employeeId;
            this.employeeEntity = data.employeeEntity;
            if (this.employeeEntity == null) this.employeeEntity = new EmployeeEntity();
        }
        this.IsEdit = false;
    }
}