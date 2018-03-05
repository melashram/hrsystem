import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HrsystemProjectModule } from './project/project.module';
import { HrsystemEmployeeModule } from './employee/employee.module';
import { HrsystemItUserModule } from './it-user/it-user.module';
import { HrsystemHumanResourceUserModule } from './human-resource-user/human-resource-user.module';
import { HrsystemRequestModule } from './request/request.module';
import { HrsystemTicketModule } from './ticket/ticket.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        HrsystemProjectModule,
        HrsystemEmployeeModule,
        HrsystemItUserModule,
        HrsystemHumanResourceUserModule,
        HrsystemRequestModule,
        HrsystemTicketModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemEntityModule {}
