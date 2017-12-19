import {Component} from "@angular/core";
import {MenuModel} from "../../menu-model";
import {Http} from "@angular/http";
import {PanelEntity} from "../MaterialComponent/panel/PanelEntity.Component";
import {AuthService} from "./Auth.Service";
// import {AuthService} from "../../Modules/Auth/Auth.Service";
// import {UserEntity} from "../../Modules/User/User.Entity";
// import {UserService} from "../../Modules/User/User.Service";

// import {LayerAccessControlEntity} from "../../Modules/LayerAccessControl/LayerAccessControl.Entity";

@Component({
    selector: 'public-header',
    templateUrl: './Header.Component.html',
    styleUrls: ['./Header.Component.css']
})
export class HeaderComponent {
    panelList : PanelEntity[] = [];
    constructor(public Http: Http,
                private AuthService: AuthService
                // , public AuthService: AuthService
    ) {
        this.AuthService.GetRouteInfo().subscribe(x =>{

            let myRequestPanel : PanelEntity = new PanelEntity();
            myRequestPanel.title = "Việc tôi yêu cầu";
            myRequestPanel.url = "MyRequest";
            myRequestPanel.isShow[4] = false;
            myRequestPanel.isShow[6] = false;
            myRequestPanel.isAccessed = x.some(value => {
                return value.method == "GET" && value.route == "view/myRequests";
            });
            this.panelList.push(myRequestPanel);
            let relatedPanel : PanelEntity = new PanelEntity();
            relatedPanel.title = "Công việc liên quan";
            relatedPanel.url = "Related";
            relatedPanel.isShow[4] = false;
            relatedPanel.isShow[6] = false;
            relatedPanel.isAccessed = x.some(value => {
                return value.method == "GET" && value.route == "view/relateds";
            });
            this.panelList.push(relatedPanel);
            let myWorkPanel : PanelEntity = new PanelEntity();
            myWorkPanel.title = "Việc tôi được giao";
            myWorkPanel.url = "MyWork";
            myWorkPanel.isShow[3] = false;
            myWorkPanel.isShow[6] = false;
            myWorkPanel.isAccessed = x.some(value => {
                return value.method == "GET" && value.route == "view/myWorks";
            });
            this.panelList.push(myWorkPanel);
            let teamWorkPanel : PanelEntity = new PanelEntity();
            teamWorkPanel.title = "Công việc của team";
            teamWorkPanel.url = "Team";
            teamWorkPanel.isShow[3] = false;
            teamWorkPanel.isAccessed = x.some(value => {
                return value.method == "GET" && value.route == "view/teams";
            });
            this.panelList.push(teamWorkPanel);
            let ITWorkPanel : PanelEntity = new PanelEntity();
            ITWorkPanel.title = "Công việc của bộ phận IT";
            ITWorkPanel.url = "IT";
            ITWorkPanel.isShow[3] = false;
            ITWorkPanel.isAccessed = x.some(value => {
                return value.method == "GET" && value.route == "view/iTs";
            });
            this.panelList.push(ITWorkPanel);
        });
    }
}