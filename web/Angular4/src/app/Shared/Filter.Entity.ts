import {URLSearchParams} from "@angular/http"

export class FilterEntity {
    skip: number;
    take: number;
    orderBy: string;
    orderType: string;

    constructor(FilterEntity: any = null) {
        if (FilterEntity == null) {
            this.skip = 0;
            this.take = 10;
            this.orderBy = "id";
            this.orderType = "none";
        } else {
            this.skip = FilterEntity.Skip;
            this.take = FilterEntity.take;
            this.orderBy = FilterEntity.OrderBy;
            this.orderType = FilterEntity.Type;
        }
    }

    ToParams(): URLSearchParams {
        let params = new URLSearchParams();
        for (let key in this) {
            if (this.hasOwnProperty(key)) {
                params.set(key, this[key.toString()]);
            }
        }
        return params;
    }
}

