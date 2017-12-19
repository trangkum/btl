import {Component, Input} from "@angular/core";
import {Http} from "@angular/http";
import {PanelEntity} from "./PanelEntity.Component";
import {ViewService} from "../../../Modules/View/View.Service";
// import {AuthService} from "../../Modules/Auth/Auth.Service";
// import {UserEntity} from "../../Modules/User/User.Entity";
// import {UserService} from "../../Modules/User/User.Service";

// import {LayerAccessControlEntity} from "../../Modules/LayerAccessControl/LayerAccessControl.Entity";

@Component({
    selector: 'panel',
    templateUrl: './Panel.Component.html',
    styleUrls: ['./Panel.Component.css']
})
export class PanelComponent {
    panel : PanelEntity = new PanelEntity();
    @Input("panel")
    set _panel(_panel: PanelEntity) {
        this.panel = _panel;
        if(this.panel.isAccessed){
            this.viewService.CountUnRead(this.panel.url[0].toLowerCase() + this.panel.url.substr(1,this.panel.url.length)+"s").subscribe(countUnread =>{
                this.panel.badges = [];
                this.panel.badges.push(countUnread.All);
                this.panel.badges.push(countUnread.New);
                this.panel.badges.push(countUnread.InProgress);
                this.panel.badges.push(countUnread.Resolved);
                this.panel.badges.push(countUnread.Feedback);
                this.panel.badges.push(countUnread.OutOfDate);
                this.panel.badges.push(countUnread.Closed);
                this.panel.badges.push(countUnread.All);
            });
            setInterval(()=>{
                this.viewService.CountUnRead(this.panel.url[0].toLowerCase() + this.panel.url.substr(1,this.panel.url.length)+"s").subscribe(countUnread =>{
                    this.panel.badges = [];
                    this.panel.badges.push(countUnread.All);
                    this.panel.badges.push(countUnread.New);
                    this.panel.badges.push(countUnread.InProgress);
                    this.panel.badges.push(countUnread.Resolved);
                    this.panel.badges.push(countUnread.Feedback);
                    this.panel.badges.push(countUnread.OutOfDate);
                    this.panel.badges.push(countUnread.Closed);
                    this.panel.badges.push(countUnread.All);
                })
            },60000);
        }
    }
    public isPanel1Collapse: boolean = false;
    hiddenDivId: string = this.MakeRandomId();
    panelDivId: string = this.MakeRandomId();

    constructor(public Http: Http, private viewService : ViewService
                // private UserService: UserService
                // , public AuthService: AuthService
    ) {
    }

    collapse() {
        if(!this.panel.isAccessed) return;
        if (!document.getElementById(this.panelDivId).classList.contains("collapsing")) {
            this.isPanel1Collapse = !this.isPanel1Collapse;
            document.getElementById(this.hiddenDivId).click();
        }
    }


    MakeRandomId(): string {
        let text = "";
        let possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (let i = 0; i < 5; i++)
            text += possible.charAt(Math.floor(Math.random() * possible.length));
        return text;
    }
}