import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HrsystemSharedModule} from '../shared';

import {
    TicketAvailableComponent,
    ticketAvailableRoute,
} from './';

const ENTITY_STATES = [
    ...ticketAvailableRoute,
];
@NgModule({
    imports: [
        HrsystemSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TicketAvailableComponent,
    ],
    entryComponents: [
        TicketAvailableComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemTicketAvailableModule {
}
