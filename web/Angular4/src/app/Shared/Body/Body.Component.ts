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
            if (splitPath.length > 1) {
                for (let i = splitPath.length - 1; i > 0; i--) {
                    this.splitPath.push(splitPath[i]);
                }
            }
            // if(event.url) {
            //     console.log(event.url);
            // }
        });
    }
}