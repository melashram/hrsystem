import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage, LocalStorageService, SessionStorageService  } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { HrsystemSharedModule, UserRouteAccessService } from './shared';
import { HrsystemAppRoutingModule} from './app-routing.module';
import { HrsystemHomeModule } from './home/home.module';
import { HrsystemAdminModule } from './admin/admin.module';
import { HrsystemAccountModule } from './account/account.module';
import { HrsystemEntityModule } from './entities/entity.module';
import {HrsystemTicketAvailableModule, TicketAvailablePopupComponent} from './ticket-available';
import { HrsystemTicketHritModule} from './ticket-hrit';
import { PaginationConfig } from './blocks/config/uib-pagination.config';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';
import { TicketAvailableComponent } from './ticket-available/ticket-available.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { TicketHritComponent } from './ticket-hrit/ticket-hrit.component';

@NgModule({
    imports: [
        BrowserModule,
        HrsystemAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        HrsystemSharedModule,
        HrsystemHomeModule,
        HrsystemAdminModule,
        HrsystemAccountModule,
        HrsystemEntityModule,
        HrsystemTicketAvailableModule,
        HrsystemTicketHritModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent,
        CreateTicketComponent,

    ],
    providers: [
        ProfileService,
        PaginationConfig,
        UserRouteAccessService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [
                LocalStorageService,
                SessionStorageService
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [
                JhiEventManager
            ]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [
                Injector
            ]
        }
    ],
    bootstrap: [ JhiMainComponent ]
})
export class HrsystemAppModule {}
