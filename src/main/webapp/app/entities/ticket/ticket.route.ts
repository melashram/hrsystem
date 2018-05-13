import {Routes} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {TicketComponent} from './ticket.component';
import {TicketDetailComponent} from './ticket-detail.component';
import {TicketPopupComponent} from './ticket-dialog.component';
import {TicketDeletePopupComponent} from './ticket-delete-dialog.component';
import {TickethritPopupComponent} from './ticket-dialoghrit.component';

export const ticketRoute: Routes = [
    {
        path: 'ticket',
        component: TicketComponent,
        data: {
            authorities: [],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ticket/:id',
        component: TicketDetailComponent,
        data: {
            authorities: ['ROLE_EMPLOYEE', 'ROLE_IT', 'ROLE_HR'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService]
    }
    , {
        path: 'ticket/:id/assigntome',
        component: TicketComponent,
        data: {
            authorities: ['ROLE_IT' , 'ROLE_HR'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService]
    }
    , {
        path: 'ticket/hrit',
        component: TicketComponent,
        data: {
            authorities: ['ROLE_IT' , 'ROLE_HR'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ticket/userticket',
        component: TicketComponent,
        data: {
            authorities: ['ROLE_EMPLOYEE', 'ROLE_IT', 'ROLE_HR'],
            pageTitle: 'UserTickets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ticketPopupRoute: Routes = [
    {
        path: 'ticket-new',
        component: TicketPopupComponent,
        data: {
            authorities: [],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ticket/:id/edit',
        component: TicketPopupComponent,
        data: {
            authorities: ['ROLE_HR' , 'ROLE_IT' , 'ROLE_ADMIN'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ticket/:id/delete',
        component: TicketDeletePopupComponent,
        data: {
            authorities: ['ROLE_IT' , 'ROLE_HR'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'tickethrit/:id/edit',
        component: TicketPopupComponent,
        data: {
            authorities: ['ROLE_HR' , 'ROLE_IT'],
            pageTitle: 'Tickets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
