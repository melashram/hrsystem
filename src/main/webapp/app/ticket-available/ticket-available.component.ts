import {Component, OnInit} from '@angular/core';
import {Ticket} from '../entities/ticket/ticket.model';
import {Subscription} from 'rxjs/Subscription';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import {Principal} from '../shared';
import {TicketService} from '../entities/ticket/ticket.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {TicketStatus, TicketStatusService} from '../entities/ticket-status';
import {RequestService} from '../entities/request';

@Component({
    selector: 'jhi-ticket-available',
    templateUrl: './ticket-available.component.html',
    styles: []
})
export class TicketAvailableComponent implements OnInit {

    tickets: Ticket[];
    ticket: Ticket;
    ticketstatuses: TicketStatus[];

    requestsIt: Request[];
    requestsHr: Request[];

    HRtickets: Ticket[];
    ITtickets: Ticket[];
    searchTicketsName: Ticket[];

    testString: '';

    searchCategories = ['A' , 'B' , 'C' , 'D'];

    searchClicked: boolean;

    currentAccount: any;
    eventSubscriber: Subscription;
    searchValueIt: string;
    searchValueHr: string;

    searchValueNameIt: string;
    searchValueNameHr: string;
    searchValueTicketStatusIt: string;
    searchValueTicketStatusHr: string;
    searchValueRequestTypeIt: string;
    searchRequestObject: Request;
    searchValueRequestTypeHr: string;

    isSaving: boolean;

    constructor(
        private ticketService: TicketService,
        private jhiAlertService: JhiAlertService,
        private ticketStatusService: TicketStatusService,
        private requestService: RequestService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadHRTickets() {
        this.ticketService.HRTicketquery().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.HRtickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadITTickets() {
        this.ticketService.ITTicketquery().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.ITtickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadAll() {
        this.ticketService.query().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.tickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadSearchByNameIt() {
        this.ticketService.SearchNameTicketItquery(this.searchValueIt , this.searchValueTicketStatusIt , this.searchValueRequestTypeIt).subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.searchTicketsName = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadSearchByNameHr() {
        this.ticketService.SearchNameTicketHrquery(this.searchValueHr , this.searchValueTicketStatusHr , this.searchValueRequestTypeHr).subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.searchTicketsName = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadTicketStatus() {
        this.ticketStatusService.query().subscribe(
            (res: HttpResponse<TicketStatus[]>) => {
                this.ticketstatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadRequestTypesIt() {
        this.requestService.itRequestsQuery().subscribe(
            (res: HttpResponse<Request[]>) => {
                this.requestsIt = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadRequestTypesHr() {
        this.requestService.hrRequestsQuery().subscribe(
            (res: HttpResponse<Request[]>) => {
                this.requestsHr = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.loadHRTickets();
        this.loadITTickets();
        this.loadTicketStatus();
        this.loadRequestTypesIt();
        this.loadRequestTypesHr();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTickets();
        this.searchClicked = false;
        this.fillArray();
    }

    searchBarOnClick() {
        if (this.searchClicked === false) {
            this.searchClicked = true;
            console.log('search clicked true');

            if (this.searchValueIt !== undefined || this.searchValueTicketStatusIt !== undefined || this.searchValueRequestTypeIt !== undefined) {
                this.loadSearchByNameIt();
            }

            if (this.searchValueHr !== undefined || this.searchValueTicketStatusHr !== undefined || this.searchValueRequestTypeHr !== undefined) {
                this.loadSearchByNameHr();
            }
        }else {
            this.searchClicked = false;
            console.log('search clicked false');

        }

    }

    fillArray() {
        this.searchCategories.push('Name');
        this.searchCategories.push('Date');
        this.searchCategories.push('Assigned to who');
    }

    selectChangeHandler(event: any) {
        this.testString = event.target.value;
    }

    trackId(index: number, item: Ticket) {
        return item.id;
    }
    registerChangeInTickets() {
        this.eventSubscriber = this.eventManager.subscribe('ticketListModification', (response) => this.loadHRTickets());
        this.eventSubscriber = this.eventManager.subscribe('ticketListModification', (response) => this.loadITTickets());
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    searchView() {
        if (this.searchClicked === false) {
            this.searchClicked = true;
            console.log('search clicked true');
        }else {
            this.searchClicked = false;
            console.log('search clicked false');
        }
    }
}
