import {SideNavComponent} from '../../side-nav/side-nav.component';
import {Injectable} from '@angular/core';
import {MatDialog} from '@angular/material';
import {LoginDialogComponent} from '../../login-dialog/login-dialog.component';
import {RegisterDialogComponent} from '../../register-dialog/register-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(public dialog: MatDialog) { }

  openLoginDialog() {
    this.dialog.open(LoginDialogComponent, {
      width: '250px',
      data: {}
    });
  }

  openRegisterDialog() {
    this.dialog.open(RegisterDialogComponent, {
      width: '250px',
      data: {}
    });
  }

  closeAllDialogs() {
    this.dialog.closeAll();
  }


}
