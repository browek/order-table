import { DialogService } from '@app/shared/services';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { NotificationService } from '@app/shared/services/notification.service';
import { NotificationResponse } from '@app/shared/models/notification-response';

@Component({
  selector: 'app-user-notifications',
  templateUrl: './user-notifications.component.html',
  styleUrls: ['./user-notifications.component.scss']
})
export class UserNotificationsComponent implements OnInit {

  arrNotifications: NotificationResponse[] = [];

  constructor(
    private dialogService: DialogService,
    private toastr: ToastrService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.fetchArrNotifications();
  }

  fetchArrNotifications(): void {
    this.notificationService.getUserNotifications()
      .subscribe(response => {
        this.arrNotifications = response;
      });
  }

  setAsDisplayed(notificationId: number) {
    this.notificationService.setAsDisplayed(notificationId)
      .subscribe(response => {
        this.arrNotifications = this.arrNotifications.filter(elem => elem.id = notificationId);
      });
  }
}
