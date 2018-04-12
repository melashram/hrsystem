import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HrsystemSharedModule} from '../shared';

import {
    TicketAvailableComponent,
    ticketAvailableRoute,
    ticketAvailablePopupRoute,
    TicketAvailablePopupService
} from './';
import {TicketAvailablePopupComponent} from './ticket-available-dialog.component';

const ENTITY_STATES = [
    ...ticketAvailableRoute,
    ...ticketAvailableRoute
];
@NgModule({
    imports: [
        HrsystemSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TicketAvailableComponent,
        TicketAvailablePopupComponent
    ],
    entryComponents: [
        TicketAvailableComponent,
        TicketAvailablePopupComponent
    ],
    providers:[
        TicketAvailablePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemTicketAvailableModule {
}
