import {Component} from '@angular/core'
import {Router} from "@angular/router";

@Component({
    selector: 'public-body',
    templateUrl: './body.component.html',
    styleUrls: ['./body.component.css']
})

export class BodyComponent {
    public splitPath: string[] = [];

    constructor(private router: Router) {
        this.router.events.subscribe((event) => {
            console.log(event);
            let splitPath: string[] = (event as any).url.split("/");
            this.splitPath = [];
            if (splitPath.length > 2) {
                for (let i =  2; i<splitPath.length; i++) {
                    this.splitPath.push(splitPath[i]);
                }
            }
            // if(event.url) {
            //     console.log(event.url);
            // }
        });
    }
}