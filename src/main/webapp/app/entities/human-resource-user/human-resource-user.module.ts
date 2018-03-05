import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HrsystemSharedModule } from '../../shared';
import { HrsystemAdminModule } from '../../admin/admin.module';
import {
    HumanResourceUserService,
    HumanResourceUserPopupService,
    HumanResourceUserComponent,
    HumanResourceUserDetailComponent,
    HumanResourceUserDialogComponent,
    HumanResourceUserPopupComponent,
    HumanResourceUserDeletePopupComponent,
    HumanResourceUserDeleteDialogComponent,
    humanResourceUserRoute,
    humanResourceUserPopupRoute,
} from './';

const ENTITY_STATES = [
    ...humanResourceUserRoute,
    ...humanResourceUserPopupRoute,
];

@NgModule({
    imports: [
        HrsystemSharedModule,
        HrsystemAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HumanResourceUserComponent,
        HumanResourceUserDetailComponent,
        HumanResourceUserDialogComponent,
        HumanResourceUserDeleteDialogComponent,
        HumanResourceUserPopupComponent,
        HumanResourceUserDeletePopupComponent,
    ],
    entryComponents: [
        HumanResourceUserComponent,
        HumanResourceUserDialogComponent,
        HumanResourceUserPopupComponent,
        HumanResourceUserDeleteDialogComponent,
        HumanResourceUserDeletePopupComponent,
    ],
    providers: [
        HumanResourceUserService,
        HumanResourceUserPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemHumanResourceUserModule {}
