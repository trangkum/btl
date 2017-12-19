import {Component, ElementRef, Input, ViewChild} from '@angular/core';

@Component({
    selector: 'image-model',
    templateUrl: './ImageModel.component.html',
    styleUrls: ['./ImageModel.component.css']
})

export class ImageModelComponent {
    public modal: string;
    public imgContent: string;

    @ViewChild('imgRef') img: ElementRef;
    @ViewChild('imgRef2') img2: ElementRef;
    @Input('base64Content')
    set Innit(value: string) {
        if (value !== undefined){
            this.imgContent = 'data:image/jpg;base64,' + value;
            this.img.nativeElement.src = this.imgContent;
            this.img2.nativeElement.src = this.imgContent;
        }
    }
    @Input() width : string;
    @Input() height : string;

    constructor() {

    }
}