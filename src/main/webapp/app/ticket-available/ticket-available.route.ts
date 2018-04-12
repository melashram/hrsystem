import {Routes} from '@angular/router';
import {TicketAvailableComponent} from './ticket-available.component';
import {UserRouteAccessService} from '../shared';
import {TicketAvailablePopupComponent} from "./ticket-available-dialog.component";

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

export const ticketAvailablePopupRoute: Routes = [
    {
        path: 'ticket-available/:id/edit',
        component: TicketAvailablePopupComponent,
        data: {
            authorities: [],
            pageTitle: 'Tickets-available'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'

    }
];
