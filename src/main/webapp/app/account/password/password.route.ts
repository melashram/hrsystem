import { Route } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PasswordComponent } from './password.component';

export const passwordRoute: Route = {
    path: 'password',
    component: PasswordComponent,
    data: {
        authorities: ['ROLE_USER','ROLE_EMPLOYEE','ROLE_IT','ROLE_HR'],
        pageTitle: 'Password'
    },
    canActivate: [UserRouteAccessService]
};
