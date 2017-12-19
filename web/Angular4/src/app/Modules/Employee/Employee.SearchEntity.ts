import {FilterEntity} from "../../Shared/Filter.Entity";

export class SearchEmployeeEntity extends FilterEntity {
    employeeId: number;
    problemId: string;
    level: string;
    userId: string;
    briefName: string;
    name: string;
    constructor(employee: any = null) {
        super(employee);
        this.employeeId = employee == null ? null : employee.employeeId;
        this.problemId = employee == null ? null : employee.problemId;
        this.level = employee == null ? null : employee.level;
        this.userId = employee == null ? null : employee.userId;
        this.briefName = employee == null ? null : employee.briefName;
        this.name = employee == null ? null : employee.name;
    }
}