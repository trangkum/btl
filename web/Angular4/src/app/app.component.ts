import {Component} from "@angular/core";
import {DropdownComponent} from "./Shared/MaterialComponent/dropdown/dropdown.component";
import {InputDiscussionComponent} from "./Shared/MaterialComponent/InputDiscussion/InputDiscussion.component";


@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    DropdownComponent : DropdownComponent = new DropdownComponent();
    DiscussionComponent : InputDiscussionComponent = new InputDiscussionComponent();
    public static IsClickDatePicker : boolean = false;
    constructor() {
    }

    public turnOffNgbDate() {
        if (!AppComponent.IsClickDatePicker) {
            let t = document.getElementsByTagName("ngb-datepicker");
            for (let i = 0; i < t.length; i++) {
                t[i].parentNode.childNodes.forEach(y => {
                    if (y.nodeName == 'INPUT') {
                        (y as any).click();
                    }
                })
            }
        }
    }

    ngOnInit() {

    }
}
