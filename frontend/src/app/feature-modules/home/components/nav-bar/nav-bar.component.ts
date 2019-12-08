import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { AuthService } from '@shared/services/auth.service';
import { DialogService } from '@shared/services/dialog.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  constructor(public authService: AuthService, private dialogService: DialogService, public dialog: MatDialog) { }

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


