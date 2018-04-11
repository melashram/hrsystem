import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import {Ticket} from "../entities/ticket";

@Component({
    selector: 'jhi-ticket-available-dialog',
    templateUrl: './ticket-available.component.html'
})

export class TicketAvailableDialogComponent implements OnInit{

    tickets: Ticket[];
    ngOnInit(){
    }

}
