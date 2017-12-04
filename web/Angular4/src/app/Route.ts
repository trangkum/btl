import {RouterModule, Routes} from "@angular/router";
//import {RegisterComponent} from "./Component/Registry/Register/Register.Component";
//import {SourceDomainComponent} from "./Component/Registry/SourceDomain/SourceDomain.Component";
//import {DestinationDomainComponent} from "./Component/Registry/DestinationDomain/DestinationDomain.Component";
import {HomeComponent} from "./Modules/Home/Home.Component";
import {MyRequestComponent} from "./Modules/MyRequest/MyRequest.Component";
//[IMPORT MODULE]
// import {[MODULE]Component} from "./Modules/[MODULE]/[MODULE].Component";
// import {MyRequestComponent} from "./Modules/shape/shape.Component";
// import {[MODULE]Component} from "./Modules/[MODULE]/[MODULE].Component";
//[END]
const routes: Routes = [
    //{
    //    path: 'Registry/SourceDomains',
    //    component: SourceDomainComponent
    //},
    {
        path: 'Home',
        component: HomeComponent,
        // canActivate: [AuthGuard]
    },
//[IMPORT MODULE]
// {    path: '[MODULE]',    component: [MODULE]Component},
//     {path: 'edges', component: edgeComponent},
//     {path: 'files', component: fileComponent},
//     {path: 'points', component: pointComponent},
//     {path: 'problems', component: problemComponent},
//     {path: 'shapes', component: MyRequestComponent},
    {path: 'MyRequest', component: MyRequestComponent},
    // {path: 'board', component: BoardComponent},
// {    path: '[MODULE]',    component: [MODULE]Component},
    //[END]
    {
        path: '**',
        redirectTo: 'Home',
    },
    {
        path: 'Fams',
        redirectTo: 'Fams/PurchaseRequest',
        pathMatch: 'full'
    },
    {
        path: 'List',
        redirectTo: 'List/Vendor',
        pathMatch: 'full'
    },
    {
        path: 'Permission',
        redirectTo: 'Permission/User',
        pathMatch: 'full'
    }
];
export const Routing = RouterModule.forRoot(routes);
