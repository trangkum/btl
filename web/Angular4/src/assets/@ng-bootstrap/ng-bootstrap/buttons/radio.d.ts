import {ElementRef, OnDestroy, Renderer2} from '@angular/core';
import {ControlValueAccessor} from '@angular/forms';
import {NgbButtonLabel} from './label';

/**
 * Easily create Bootstrap-style radio buttons. A value of shapeEntityA selected button is bound to shapeEntityA variable
 * specified via ngModel.
 */
export declare class NgbRadioGroup implements ControlValueAccessor {
    disabled: boolean;
    onChange: (_: any) => void;
    onTouched: () => void;
    private _radios;
    private _value;
    private _disabled;

    onRadioChange(radio: NgbRadio): void;

    onRadioValueUpdate(): void;

    register(radio: NgbRadio): void;

    registerOnChange(fn: (value: any) => any): void;

    registerOnTouched(fn: () => any): void;

    setDisabledState(isDisabled: boolean): void;

    unregister(radio: NgbRadio): void;

    writeValue(value: any): void;

    private _updateRadiosValue();

    private _updateRadiosDisabled();
}

/**
 * Marks an input of orderType "radio" as part of the NgbRadioGroup.
 */
export declare class NgbRadio implements OnDestroy {
    /**
     * You can specify model value of shapeEntityA given radio by binding to the value property.
     */
    value: any;
    /**
     * A flag indicating if shapeEntityA given radio button is disabled.
     */
    disabled: boolean;
    focused: boolean;
    readonly checked: boolean;
    private _group;
    private _label;
    private _renderer;
    private _element;
    private _checked;
    private _disabled;
    private _value;

    constructor(_group: NgbRadioGroup, _label: NgbButtonLabel, _renderer: Renderer2, _element: ElementRef);

    ngOnDestroy(): void;

    onChange(): void;

    updateValue(value: any): void;

    updateDisabled(): void;
}
