import { NavService } from './shared/services/nav.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule, FormControl } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {
  MatMenuModule,
  MatButtonModule,
  MatDialogModule,
  MatFormFieldModule,
  ErrorStateMatcher,
  ShowOnDirtyErrorStateMatcher,
  MatInputModule,
  MatIconModule,
  MatSidenavModule,
  MatButtonToggleModule,
  MatRadioModule} from '@angular/material';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { RegisterDialogComponent } from './register-dialog/register-dialog.component';
import { CompareValidatorDirective } from './shared/validators/compare-validator.directive';
import { AuthService } from './shared/auth/auth.service';
import { HomeComponent } from './home/home.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    CompareValidatorDirective,
    HomeComponent,
    SideNavComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatMenuModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FontAwesomeModule,
    MatSidenavModule,
    MatButtonToggleModule,
    MatRadioModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDFhBAj941EfWxzxb6R7EGXnRLflnnvtyo'
    })
  ],
  providers: [{provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}, AuthService, NavService],
  bootstrap: [AppComponent],
  entryComponents: [LoginDialogComponent, RegisterDialogComponent, SideNavComponent]
})
export class AppModule { }
