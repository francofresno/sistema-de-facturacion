import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { ClientsComponent } from './clients/clients.component';
import { ClientService } from './clients/client.service';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FormComponent } from "./clients/form.component";
import { PaginatorComponent } from './paginator/paginator.component';
import { ProfileComponent } from './clients/profile/profile.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es-AR';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { LoginComponent } from './users/login.component';
import { AuthGuard } from './users/guards/auth.guard';
import { RoleGuard } from './users/guards/role.guard';
import { TokenInterceptor } from './users/interceptors/token.interceptor';
import { AuthInterceptor } from './users/interceptors/auth.interceptor';
import { InvoiceDetailComponent } from './invoices/invoice-detail.component';
import { InvoiceService } from './invoices/services/invoice.service';
import { InvoicesComponent } from './invoices/invoices.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

registerLocaleData(localeES, 'es');

const routes: Routes = [
  {path: '', redirectTo: '/clients', pathMatch: 'full'},
  {path: 'clients', component: ClientsComponent},
  {path: 'clients/page/:page', component: ClientsComponent},
  {path: 'clients/form', component: FormComponent, canActivate:[AuthGuard, RoleGuard], data: {role:'ROLE_ADMIN'}},
  {path: 'clients/form/:id', component: FormComponent, canActivate:[AuthGuard, RoleGuard], data: {role:'ROLE_ADMIN'}},
  {path: 'login', component: LoginComponent},
  {path: 'invoices/:id', component: InvoiceDetailComponent, canActivate:[AuthGuard, RoleGuard], data: {role:'ROLE_USER'}},
  {path: 'invoices/form/:clientId', component: InvoicesComponent, canActivate:[AuthGuard, RoleGuard], data: {role:'ROLE_ADMIN'}},
];

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    ClientsComponent,
    HeaderComponent,
    FormComponent,
    PaginatorComponent,
    ProfileComponent,
    LoginComponent,
    InvoiceDetailComponent,
    InvoicesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMomentDateModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [
    ClientService,
    InvoiceService,
    {provide: LOCALE_ID, useValue: 'es'},
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
