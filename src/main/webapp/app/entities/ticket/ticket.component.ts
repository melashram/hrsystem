import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Ticket } from './ticket.model';
import { TicketService } from './ticket.service';
import { Principal } from '../../shared';
import {Observable} from 'rxjs/Observable';

@Component({
    selector: 'jhi-ticket',
    templateUrl: './ticket.component.html'
})
export class TicketComponent implements OnInit, OnDestroy {

    tickets: Ticket[];
    ticket: Ticket;
    userTickets:Ticket[];
    isSaving: boolean;
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private ticketService: TicketService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadUserTicket(){
        this.ticketService.UserTicketquery().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.userTickets = res.body;
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
    ngOnInit() {
        this.isSaving = false;
        this.loadAll();
        this.loadUserTicket();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTickets();
    }

    save() {
        this.isSaving = true;
        if (this.ticket.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ticketService.update(this.ticket));
        } else {
            this.subscribeToSaveResponse(
                this.ticketService.create(this.ticket));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Ticket>>) {
        result.subscribe((res: HttpResponse<Ticket>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Ticket) {
        this.eventManager.broadcast({ name: 'ticketListModification', content: 'OK'});
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
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
}
