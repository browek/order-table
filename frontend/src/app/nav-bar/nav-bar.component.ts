import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../register-dialog/register-dialog.component';
import {DialogService} from '../shared/services/dialog.service';
import {AUTHORIZATION_HEADER, AuthService} from '../shared/auth/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  constructor(public authService: AuthService, private dialogService: DialogService) { }

  ngOnInit() {
  }

  loginDialog() {
      this.dialogService.openLoginDialog();
  }
  registerDialog() {
    this.dialogService.openRegisterDialog();
  }

  isLogged() {
    return this.authService.isLogged();
  }

  isAdmin() {
    return this.authService.isAdmin();
  }

  isClient() {
    return this.authService.isClient();
  }

  isRestaurateur() {
    return this.authService.isRestaurateur();
  }

  logout() {
    this.authService.logout();
  }

}
