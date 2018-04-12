import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import {TicketService} from '../entities/ticket/ticket.service';
import {Ticket} from '../entities/ticket/ticket.model';

@Injectable()
export class TicketAvailablePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private ticketService: TicketService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.ticketService.find(id)
                    .subscribe((ticketResponse: HttpResponse<Ticket>) => {
                        const ticket: Ticket = ticketResponse.body;
                        ticket.creationDate = this.datePipe
                            .transform(ticket.creationDate, 'yyyy-MM-ddTHH:mm:ss');
                        ticket.acceptanceDate = this.datePipe
                            .transform(ticket.acceptanceDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.ticketModalRef(component, ticket);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.ticketModalRef(component, new Ticket());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    ticketModalRef(component: Component, ticket: Ticket): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.ticket = ticket;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
