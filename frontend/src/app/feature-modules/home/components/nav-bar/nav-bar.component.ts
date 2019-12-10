import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/core/services';
import { DialogService } from '@shared/services/dialog.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  notificationsCount = 0;

  constructor(
    public authService: AuthService,
    private dialogService: DialogService,
    private toastr: ToastrService) { }

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
    this.toastr.success('Wylogowano');
  }

  handleNotificationReaded(notificationsCount: number): void {
    this.notificationsCount = notificationsCount;
  }
}


