import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NotificationResponse } from '@app/shared/models/notification-response';
import { NotificationService } from '@app/shared/services/notification.service';

@Component({
  selector: 'app-reservation-notifications',
  templateUrl: './reservation-notifications.component.html',
  styleUrls: ['./reservation-notifications.component.scss']
})
export class ReservationNotificationsComponent implements OnInit {

  @Output()
  notificationReaded = new EventEmitter<number>();

  arrNotifications: NotificationResponse[] = [];

  constructor(private notificationService: NotificationService) { }

  ngOnInit() {
    this.fetchArrNotifications();
  }

  setAsDisplayed(notificationId: number) {
    this.notificationService.setAsDisplayed(notificationId)
      .subscribe(response => {
        this.arrNotifications = this.arrNotifications.filter(elem => elem.id !== notificationId);
        this.notificationReaded.emit(this.arrNotifications.length);
      });
  }

  fetchArrNotifications(): void {
    this.notificationService.getUserNotifications()
      .subscribe(response => {
        this.arrNotifications = response;
        this.notificationReaded.emit(this.arrNotifications.length);
      });
  }
}
