import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HrsystemSharedModule } from '../../shared';
import { HrsystemAdminModule } from '../../admin/admin.module';
import {
    ItUserService,
    ItUserPopupService,
    ItUserComponent,
    ItUserDetailComponent,
    ItUserDialogComponent,
    ItUserPopupComponent,
    ItUserDeletePopupComponent,
    ItUserDeleteDialogComponent,
    itUserRoute,
    itUserPopupRoute,
} from './';

const ENTITY_STATES = [
    ...itUserRoute,
    ...itUserPopupRoute,
];

@NgModule({
    imports: [
        HrsystemSharedModule,
        HrsystemAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItUserComponent,
        ItUserDetailComponent,
        ItUserDialogComponent,
        ItUserDeleteDialogComponent,
        ItUserPopupComponent,
        ItUserDeletePopupComponent,
    ],
    entryComponents: [
        ItUserComponent,
        ItUserDialogComponent,
        ItUserPopupComponent,
        ItUserDeleteDialogComponent,
        ItUserDeletePopupComponent,
    ],
    providers: [
        ItUserService,
        ItUserPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemItUserModule {}
