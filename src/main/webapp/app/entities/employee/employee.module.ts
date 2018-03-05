import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HrsystemSharedModule } from '../../shared';
import { HrsystemAdminModule } from '../../admin/admin.module';
import {
    EmployeeService,
    EmployeePopupService,
    EmployeeComponent,
    EmployeeDetailComponent,
    EmployeeDialogComponent,
    EmployeePopupComponent,
    EmployeeDeletePopupComponent,
    EmployeeDeleteDialogComponent,
    employeeRoute,
    employeePopupRoute,
} from './';

const ENTITY_STATES = [
    ...employeeRoute,
    ...employeePopupRoute,
];

@NgModule({
    imports: [
        HrsystemSharedModule,
        HrsystemAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        EmployeeComponent,
        EmployeeDetailComponent,
        EmployeeDialogComponent,
        EmployeeDeleteDialogComponent,
        EmployeePopupComponent,
        EmployeeDeletePopupComponent,
    ],
    entryComponents: [
        EmployeeComponent,
        EmployeeDialogComponent,
        EmployeePopupComponent,
        EmployeeDeleteDialogComponent,
        EmployeeDeletePopupComponent,
    ],
    providers: [
        EmployeeService,
        EmployeePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HrsystemEmployeeModule {}
