import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import {JhiEventManager, JhiAlertService, JhiParseLinks} from 'ng-jhipster';

import { Ticket } from './ticket.model';
import { TicketService } from './ticket.service';
import {Principal, User} from '../../shared';
import {TicketStatus, TicketStatusService} from '../ticket-status';

@Component({
    selector: 'jhi-ticket',
    templateUrl: './ticket.component.html'
})
export class TicketComponent implements OnInit, OnDestroy {
tickets: Ticket[];
    currentAccount: any;
    eventSubscriber: Subscription;

    ticketstatuses: TicketStatus[];

    queryCount: any;
    totalItems: any;
    links: any;
    predicate: any;
    reverse: any;
    page: any;
    itemsPerPage: any;



    constructor(
        private ticketService: TicketService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private ticketStatusService: TicketStatusService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
    ) {
    }

    loadAll() {
        this.ticketService.query().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.tickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    loadAlls() {
        this.ticketService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()}).subscribe(
            (res: HttpResponse<Ticket[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpResponse<any>) => this.onError(res.body)
        );

        console.log(this.page);
    }

    ngOnInit() {
        this.loadAll();
        this.loadTicketStatus();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTickets();
    }

    loadTicketStatus() {
        this.ticketStatusService.query().subscribe(
            (res: HttpResponse<TicketStatus[]>) => {
                this.ticketstatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Ticket) {
        return item.id;
    }
    registerChangeInTickets() {
        this.eventSubscriber = this.eventManager.subscribe('ticketListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.tickets = data;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

}
