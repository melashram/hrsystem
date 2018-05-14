import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HrsystemSharedModule} from '../shared';
import {
    TicketHritComponent,
    ticketHritRoute
} from './';

const ENTITY_STATES = [
    ...ticketHritRoute,
];

@NgModule({
    imports: [
        HrsystemSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TicketHritComponent,
    ],
    entryComponents: [
        TicketHritComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class HrsystemTicketHritModule {}
