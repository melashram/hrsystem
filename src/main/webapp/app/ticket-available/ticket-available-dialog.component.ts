import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import {Ticket} from '../entities/ticket';
import {Request, RequestService} from '../entities/request';
import {User, UserService} from '../shared';
import {TicketStatus, TicketStatusService} from '../entities/ticket-status';
import {HumanResourceUser, HumanResourceUserService} from '../entities/human-resource-user';
import {TicketService} from '../entities/ticket/ticket.service';
import {TicketAvailablePopupService} from './ticket-available-popup.service';
import {TicketDialogComponent} from '../entities/ticket/ticket-dialog.component';

@Component({
    selector: 'jhi-ticket-available-dialog',
    templateUrl: './ticket-available.component.html'
})

export class TicketAvailableDialogComponent implements OnInit {

    ticket: Ticket;
    isSaving: boolean;
    requests: Request[];

    ticketstatuses: TicketStatus[];

    assignedtos: HumanResourceUser[];

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private ticketService: TicketService,
        private requestService: RequestService,
        private ticketStatusService: TicketStatusService,
        private humanResourceUserService: HumanResourceUserService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.requestService
            .query({filter: 'ticket-is-null'})
            .subscribe((res: HttpResponse<Request[]>) => {
                if (!this.ticket.request || !this.ticket.request.id) {
                    this.requests = res.body;
                } else {
                    this.requestService
                        .find(this.ticket.request.id)
                        .subscribe((subRes: HttpResponse<Request>) => {
                            this.requests = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.ticketStatusService
            .query({filter: 'ticket-is-null'})
            .subscribe((res: HttpResponse<TicketStatus[]>) => {
                if (!this.ticket.ticketStatus || !this.ticket.ticketStatus.id) {
                    this.ticketstatuses = res.body;
                } else {
                    this.ticketStatusService
                        .find(this.ticket.ticketStatus.id)
                        .subscribe((subRes: HttpResponse<TicketStatus>) => {
                            this.ticketstatuses = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userService.query()
            .subscribe((res: HttpResponse<User[]>) => { this.users = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
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

    saveHRIT() {
        this.isSaving = true;
        if (this.ticket.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ticketService.updateHRIT(this.ticket));
            console.log(this.ticket);
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
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackRequestById(index: number, item: Request) {
        return item.id;
    }

    trackTicketStatusById(index: number, item: TicketStatus) {
        return item.id;
    }

    trackHumanResourceUserById(index: number, item: HumanResourceUser) {
        return item.id;
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-ticket-available-popup',
    template: ''
})

export class TicketAvailablePopupComponent implements OnInit , OnDestroy {
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ticketAvailablePopupService: TicketAvailablePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ticketAvailablePopupService
                    .open(TicketDialogComponent as Component, params['id']);
            } else {
                this.ticketAvailablePopupService
                    .open(TicketDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }

}
