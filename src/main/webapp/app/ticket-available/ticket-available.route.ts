import {Routes} from '@angular/router';
import {TicketAvailableComponent} from './ticket-available.component';
import {UserRouteAccessService} from '../shared';

export const ticketAvailableRoute: Routes = [
    {
        path: 'ticket-available',
        component: TicketAvailableComponent,
        data: {
            authorities: ['ROLE_HR'],
            pageTitle: 'Tickets-Available'
        },
        canActivate: [UserRouteAccessService]
    }
];
