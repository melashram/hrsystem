import { Component, OnInit } from '@angular/core';
import {Ticket} from "../entities/ticket/ticket.model";
import {Subscribable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {JhiAlertService, JhiEventManager} from "ng-jhipster";
import {TicketService} from "../entities/ticket/ticket.service";
import {Principal} from "../shared";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'jhi-ticket-hrit',
  templateUrl: './ticket-hrit.component.html',
  styles: []
})
export class TicketHritComponent implements OnInit {

  userTickets:Ticket[];
  currentAccount: any;
  eventSubscriber: Subscription;
  constructor(
      private ticketService: TicketService,
      private jhiAlertService: JhiAlertService,
      private eventManager: JhiEventManager,
      private principal: Principal
  ) { }

    loadUserTicket(){
        this.ticketService.UserTicketquery().subscribe(
            (res: HttpResponse<Ticket[]>) => {
                this.userTickets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

  ngOnInit() {

      this.principal.identity().then((account) => {
          this.currentAccount = account;
      });
      this.loadUserTicket();
      this.registerChangeInTickets();
  }

    registerChangeInTickets() {
        this.eventSubscriber = this.eventManager.subscribe('ticketListModification', (response) => this.loadUserTicket());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
