import { map, catchError } from 'rxjs/operators';
import { NotificationResponse } from './../models/notification-response';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  getUserNotifications(): Observable<NotificationResponse[]> {
    return this.http.get<NotificationResponse[]>('/api/client/notifications')
      .pipe(
        map(
          (response: any) => {
            return response.content;
          }
        ),
        catchError((error: any) => {
          return of([]);
        }),
      );
  }

  setAsDisplayed(notificationId: number): Observable<number> {
    const httpParams = {
      notificationId: notificationId + ''
    };

    return this.http.get<number>('/api/notifications/search/setAsDisplayed', { params: httpParams });
  }
}
