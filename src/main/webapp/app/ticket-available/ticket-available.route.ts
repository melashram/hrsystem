import {Routes} from '@angular/router';
import {TicketAvailableComponent} from './ticket-available.component';
import {UserRouteAccessService} from '../shared';

export const ticketAvailableRoute: Routes = [
    {
        path: 'ticket-available',
        component: TicketAvailableComponent,
        data: {
            authorities: ['ROLE_HR', 'ROLE_IT'],
            pageTitle: 'Tickets-Available'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ticketPopupRoute: Routes = [
    {
        path: 'ticket-available/:id/edit',
        component: TicketAvailablePopupComponent
    }
]
