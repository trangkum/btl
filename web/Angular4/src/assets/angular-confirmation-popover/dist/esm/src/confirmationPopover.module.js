import { NgModule, InjectionToken } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Positioning } from 'positioning';
import { ConfirmationPopover } from './confirmationPopover.directive';
import { ConfirmationPopoverWindow } from './confirmationPopoverWindow.component';
import { Focus } from './focus.directive';
import { ConfirmationPopoverOptions } from './confirmationPopoverOptions.provider';
export var USER_OPTIONS = new InjectionToken('confirmation popover user options');
export function optionsFactory(userOptions) {
    var options = new ConfirmationPopoverOptions();
    Object.assign(options, userOptions);
    return options;
}
var ConfirmationPopoverModule = (function () {
    function ConfirmationPopoverModule() {
    }
    ConfirmationPopoverModule.forRoot = function (options) {
        if (options === void 0) { options = {}; }
        return {
            ngModule: ConfirmationPopoverModule,
            providers: [{
                    provide: USER_OPTIONS,
                    useValue: options
                }, {
                    provide: ConfirmationPopoverOptions,
                    useFactory: optionsFactory,
                    deps: [USER_OPTIONS]
                }, Positioning]
        };
    };
    ConfirmationPopoverModule.decorators = [
        { type: NgModule, args: [{
                    declarations: [ConfirmationPopover, ConfirmationPopoverWindow, Focus],
                    imports: [CommonModule],
                    exports: [ConfirmationPopover, Focus],
                    entryComponents: [ConfirmationPopoverWindow]
                },] },
    ];
    /** @nocollapse */
    ConfirmationPopoverModule.ctorParameters = function () { return []; };
    return ConfirmationPopoverModule;
}());
export { ConfirmationPopoverModule };
//# sourceMappingURL=confirmationPopover.module.js.map