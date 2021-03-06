import { EventEmitter, ViewContainerRef, ComponentRef, OnDestroy, ElementRef, OnChanges, OnInit, ComponentFactoryResolver, Renderer2, TemplateRef } from '@angular/core';
import { ConfirmationPopoverWindow } from './confirmationPopoverWindow.component';
import { ConfirmationPopoverOptions } from './confirmationPopoverOptions.provider';
import { Positioning } from 'positioning';
/**
 * @private
 */
export interface ConfirmCancelEvent {
    clickEvent: MouseEvent;
}
/**
 * All properties can be set on the directive as attributes like so (use `ConfirmationPopoverModule.forRoot()` to configure them globally):
 * ```html
 * &lt;button
 *  class="btn btn-default"
 *  mwlConfirmationPopover
 *  [title]="title"
 *  [message]="message"
 *  placement="left"
 *  (confirm)="confirmClicked = true"
 *  (cancel)="cancelClicked = true"
 *  [(isOpen)]="isOpen"&gt;
 *   Show confirm popover!
 * &lt;/button&gt;
 * ```
 */
export declare class ConfirmationPopover implements OnDestroy, OnChanges, OnInit {
    private viewContainerRef;
    private elm;
    private defaultOptions;
    private cfr;
    private position;
    private renderer;
    private document;
    /**
     * The title of the popover.
     * Note, if you use an expression, you may want to consider using "data-title" instead of "title" so
     * that the browser doesn't show native tooltips with the angular expression listed.
     */
    title: string;
    /**
     * The body text of the popover.
     */
    message: string;
    /**
     * The text of the confirm button. Default `Confirm`
     */
    confirmText: string;
    /**
     * The text of the cancel button. Default `Cancel`
     */
    cancelText: string;
    /**
     * The placement of the popover. It can be either `top`, `right`, `bottom` or `left`. Default `top`
     */
    placement: string;
    /**
     * The bootstrap button orderType of the confirm button. It can be any supported bootstrap color orderType
     * e.g. `default`, `warning`, `danger` etc. Default `success`
     */
    confirmButtonType: string;
    /**
     * The bootstrap button orderType of the cancel button. It can be any supported bootstrap color orderType
     * e.g. `default`, `warning`, `danger` etc. Default `default`
     */
    cancelButtonType: string;
    /**
     * Set to either `confirm` or `cancel` to focus the confirm or cancel button.
     * If omitted, by default it will not focus either button.
     */
    focusButton: string;
    /**
     * Whether to hide the confirm button. Default `false`.
     */
    hideConfirmButton: boolean;
    /**
     * Whether to hide the cancel button. Default `false`.
     */
    hideCancelButton: boolean;
    /**
     * Whether to disable showing the popover. Default `false`.
     */
    isDisabled: boolean;
    /**
     * Will open or show the popover when changed.
     * Can be sugared with `isOpenChange` to emulate 2-way binding like so `[(isOpen)]="isOpen"`
     */
    isOpen: boolean;
    /**
     * A reference to shapeEntityA &lt;template&gt; tag that if set will override the popovers template. Use like so:
     * ```html
     * &lt;ng-template #customTemplate let-options="options"&gt;
     *   &lt;div [class]="'popover ' + options.placement" style="display: block"&gt;
     *     My custom template
     *   &lt;/div&gt;
     * &lt;/ng-template&gt;
     * ```
     *
     * Then pass customTemplate to the mwlConfirmationPopover directive like so `[customTemplate]="customTemplate"`
     */
    customTemplate: TemplateRef<any>;
    /**
     * Will emit when the popover is opened or closed
     */
    isOpenChange: EventEmitter<boolean>;
    /**
     * An expression that is called when the confirm button is clicked.
     */
    confirm: EventEmitter<ConfirmCancelEvent>;
    /**
     * An expression that is called when the cancel button is clicked.
     */
    cancel: EventEmitter<ConfirmCancelEvent>;
    /**
     * A custom CSS class to be added to the popover
     */
    popoverClass: string;
    /**
     * Append the element to the document body rather than the trigger element
     */
    appendToBody: boolean;
    /**
     * @private
     */
    popover: ComponentRef<ConfirmationPopoverWindow>;
    /**
     * @private
     */
    eventListeners: Function[];
    /**
     * @private
     */
    constructor(viewContainerRef: ViewContainerRef, elm: ElementRef, defaultOptions: ConfirmationPopoverOptions, cfr: ComponentFactoryResolver, position: Positioning, renderer: Renderer2, document: any);
    /**
     * @private
     */
    ngOnInit(): void;
    /**
     * @private
     */
    ngOnChanges(changes: any): void;
    /**
     * @private
     */
    ngOnDestroy(): void;
    /**
     * @private
     */
    onConfirm(event: ConfirmCancelEvent): void;
    /**
     * @private
     */
    onCancel(event: ConfirmCancelEvent): void;
    /**
     * @private
     */
    togglePopover(): void;
    private onDocumentClick(event);
    private showPopover();
    private positionPopover();
    private hidePopover();
}
