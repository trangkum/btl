// import {Component, ViewContainerRef} from '@angular/core';
// import {BottomToastsManager} from "../../Shared/CustomToaster";
// import {PagingModel} from "app/Shared/MaterialComponent/paging/paging.model";
// import {ModalComponent} from "../../Shared/MaterialComponent/modal/modal.component";
// import {SearchshapeEntity} from "./Employee.SearchEntity";
// import {ShapeService} from "./Employee.Service";
// import {EmployeeEntity} from "./Employee.Entity";
// // import {EdgeEntity} from "../edge/edge.Entity";
// import {UserEntity} from "../user/user.Entity";
// // import {ProblemEntity} from "../problem/problem.Entity";
// import {hitAreas, types} from "ngvas";
// import Line = types.Line;
//
// @Component({
//     selector: 'App-shape',
//     templateUrl: './Comment.Component.html',
//     styleUrls: ['./Comment.Component.css'],
//     providers: [ShapeService, BottomToastsManager]
// })
// export class MyRequestComponent {
//     Title: string = "shape";
//     shapeEntities: any[];
//     PagingModel = new PagingModel(7, 10, data => {
//         this.shapeService.Get().subscribe(p => {
//             this.shapeEntities = p;
//         });
//     });
//     temp = {};
//     CreatedshapeEntity: EmployeeEntity;
//     EditedshapeEntity: EmployeeEntity;
//     EditPosition: number;
//     SearchshapeEntity: SearchshapeEntity;
//     DeleteData: EmployeeEntity;
//     EditModalComponent: ModalComponent;
//     DeleteModalComponent: ModalComponent;
//     problemList: Array<ProblemEntity> = [];
//     userList: Array<UserEntity> = [];
//     edgeList: Array<EdgeEntity> = [];
//     ChoosedproblemItem: ProblemEntity = new ProblemEntity();
//     ChooseduserItem: UserEntity = new UserEntity();
//     ChoosededgeItem: EdgeEntity = new EdgeEntity();
//     SearchproblemName: string;
//     SearchuserName: string;
//     SearchedgeName: string;
//     public pixelHitArea = hitAreas.PixelHitArea;
//
//     constructor(private shapeService: ShapeService,
//                 private toastr: BottomToastsManager, vcr: ViewContainerRef) {
//         this.CreatedshapeEntity = new EmployeeEntity();
//         this.EditedshapeEntity = new EmployeeEntity();
//         this.SearchshapeEntity = new SearchshapeEntity();
//         this.Search();
//         this.EditModalComponent = new ModalComponent();
//         this.DeleteModalComponent = new ModalComponent();
//         this.toastr.setRootViewContainerRef(vcr);
//     }
//
//     Search() {
//         this.SearchshapeEntity.skip = this.PagingModel.take * this.PagingModel.Active;
//         this.SearchshapeEntity.take = this.PagingModel.take;
//         this.shapeService.Get(this.SearchshapeEntity).subscribe(p => {
//             this.shapeEntities = p;
//             this.shapeEntities.forEach(h => {
//                 h.maxX = 0;
//                 h.maxY = 0;
//                 let t: Array<Line> = [];
//                 h.edgeEntities.forEach(edge => {
//                     t.push([[edge.startX, edge.startY], [edge.endX, edge.endY]]);
//                     h.maxX = Math.max(h.maxX, edge.startX, edge.endX);
//                     h.maxY = Math.max(h.maxY, edge.startY, edge.endY);
//                 });
//                 h.render = t;
//             });
//             this.Count();
//         });
//     }
//
//     Count() {
//         this.shapeService.Count(this.SearchshapeEntity).subscribe(data => {
//             this.PagingModel.TotalPage = Math.ceil(data / this.PagingModel.take);
//         });
//     }
//
//     LoadDataToUpdateModal(shape: EmployeeEntity, index: number) {
//         this.EditedshapeEntity = new EmployeeEntity(shape);
//         this.EditPosition = index;
//         document.getElementById(this.EditModalComponent.ID).click();
//     }
//
//     LoadDataToDelete(shape: EmployeeEntity) {
//         this.DeleteData = shape;
//         document.getElementById(this.DeleteModalComponent.ID).click();
//     }
//
//     Edit() {
//         this.shapeService.Update(this.EditedshapeEntity).subscribe(p => {
//             this.shapeEntities[this.EditPosition] = p;
//             this.toastr.ShowSuccess();
//         }, e => {
//             this.toastr.ShowError(e);
//         });
//     }
//
//     Add() {
//         this.shapeService.Create(this.CreatedshapeEntity).subscribe(p => {
//             p.IsEdit = true;
//             this.shapeEntities.unshift(p);
//             this.CreatedshapeEntity = new EmployeeEntity();
//             this.toastr.ShowSuccess();
//         }, e => {
//             this.toastr.ShowError(e);
//         });
//     }
//
//     Delete() {
//         this.shapeService.Delete(this.DeleteData).subscribe(p => {
//             let indexOf = this.shapeEntities.indexOf(this.DeleteData);
//             this.shapeEntities.splice(indexOf, 1);
//             this.toastr.ShowSuccess();
//         }, e => {
//             this.toastr.ShowError(e);
//         });
//     }
//
//     Rotate(event, data) {
//         // debugger;
//         // data[0];
//     }
//
//     Test(event) {
//         console.log(event);
//         // debugger;
//     }
//
//     // Save(shapeEntity: FileEntity) {
//     //     if (shapeEntity.Id === undefined || shapeEntity.Id === null) {
//     //         this.ShapeService.Create(shapeEntity).subscribe(p => {
//     //             Object.assign(shapeEntity, p);
//     //             shapeEntity.IsEdit = false;
//     //             this.toastr.ShowSuccess();
//     //         }, e => {
//     //             this.toastr.ShowError(e);
//     //         });
//     //     } else {
//     //         this.ShapeService.Update(shapeEntity).subscribe(p => {
//     //             Object.assign(shapeEntity, p);
//     //             shapeEntity.IsEdit = false;
//     //             this.toastr.ShowSuccess();
//     //         }, e => {
//     //             this.toastr.ShowError(e);
//     //         });
//     //     }
//     // }
//     //
//     // Cancel(shapeEntity: FileEntity) {
//     //     if (shapeEntity.Id === undefined || shapeEntity.Id === null) {
//     //         this.shapeEntities.splice(0, 1);
//     //     } else {
//     //         Object.assign(shapeEntity, this.temp);
//     //         shapeEntity.IsEdit = false;
//     //     }
//     // }
// }