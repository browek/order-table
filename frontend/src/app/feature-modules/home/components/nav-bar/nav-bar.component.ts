import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/core/services';
import { DialogService } from '@shared/services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from '@app/shared/services/notification.service';
import { NotificationResponse } from '@app/shared/models/notification-response';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  arrNotifications: NotificationResponse[] = [];

  constructor(
    public authService: AuthService,
    private dialogService: DialogService,
    private toastr: ToastrService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.fetchArrNotifications();
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

  setAsDisplayed(notificationId: number) {
    this.notificationService.setAsDisplayed(notificationId)
      .subscribe(response => {
        this.arrNotifications = this.arrNotifications.filter(elem => elem.id = notificationId)
      });
  }

  fetchArrNotifications(): void {
    this.notificationService.getUserNotifications()
      .subscribe(response => {
        this.arrNotifications = response;
      });
  }

  logout() {
    this.authService.logout();
    this.toastr.success('Wylogowano');
  }
}


