import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Ticket } from './ticket.model';
import { TicketPopupService } from './ticket-popup.service';
import { TicketService } from './ticket.service';
import { HumanResourceUser, HumanResourceUserService } from '../human-resource-user';
import { Request, RequestService } from '../request';
import { Employee, EmployeeService } from '../employee';

@Component({
    selector: 'jhi-ticket-dialog',
    templateUrl: './ticket-dialog.component.html'
})
export class TicketDialogComponent implements OnInit {

    ticket: Ticket;
    isSaving: boolean;

    assigendtos: HumanResourceUser[];

    ticketrequsts: Request[];

    employees: Employee[];
    acceptanceDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private ticketService: TicketService,
        private humanResourceUserService: HumanResourceUserService,
        private requestService: RequestService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.humanResourceUserService
            .query({filter: 'ticket-is-null'})
            .subscribe((res: HttpResponse<HumanResourceUser[]>) => {
                if (!this.ticket.assigendTo || !this.ticket.assigendTo.id) {
                    this.assigendtos = res.body;
                } else {
                    this.humanResourceUserService
                        .find(this.ticket.assigendTo.id)
                        .subscribe((subRes: HttpResponse<HumanResourceUser>) => {
                            this.assigendtos = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.requestService
            .query({filter: 'ticket-is-null'})
            .subscribe((res: HttpResponse<Request[]>) => {
                if (!this.ticket.ticketRequst || !this.ticket.ticketRequst.id) {
                    this.ticketrequsts = res.body;
                } else {
                    this.requestService
                        .find(this.ticket.ticketRequst.id)
                        .subscribe((subRes: HttpResponse<Request>) => {
                            this.ticketrequsts = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.employeeService.query()
            .subscribe((res: HttpResponse<Employee[]>) => { this.employees = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackHumanResourceUserById(index: number, item: HumanResourceUser) {
        return item.id;
    }

    trackRequestById(index: number, item: Request) {
        return item.id;
    }

    trackEmployeeById(index: number, item: Employee) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-ticket-popup',
    template: ''
})
export class TicketPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ticketPopupService: TicketPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ticketPopupService
                    .open(TicketDialogComponent as Component, params['id']);
            } else {
                this.ticketPopupService
                    .open(TicketDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
