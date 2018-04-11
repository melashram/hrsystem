import {Routes} from '@angular/router';
import {TicketHritComponent} from './ticket-hrit.component';
import {UserRouteAccessService} from '../shared';


export const ticketHritRoute: Routes =[
    {
        path: 'ticket-hrit',
        component: TicketHritComponent,
        data: {
            authorities: [],
            pageTitle: 'Tickets-hrit'
        },
        canActivate: [UserRouteAccessService]
    }
];
