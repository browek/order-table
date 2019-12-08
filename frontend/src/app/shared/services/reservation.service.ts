import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface NewReservation {
  restaurantApiId:  string;
  numberOfPersons:  number;
  dateAndTime:      Date;
  message:          string;
}

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  sendReservation(reservationData: NewReservation): Observable<any> {
    return this.http.post<any>('/api/client/sendReservationRequest', reservationData);
  }
}
