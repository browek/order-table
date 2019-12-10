import { map, catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ReservationsRange, ReservationResponse } from '../models';

export interface NewReservation {
  restaurantApiId:  string;
  numberOfPersons:  number;
  dateAndTime:      Date;
  clientMessage:          string;
}

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly RESERVATIONS_PATH = 'reservationRequests';

  constructor(private http: HttpClient) { }

  sendReservation(reservationData: NewReservation): Observable<any> {
    return this.http.post<any>('/api/client/sendReservationRequest', reservationData);
  }

  getClientReservations(): Observable<any> {
    const httpParams = {
      projection: 'withRestaurant'
    };

    return this.http.get(`/api/${this.RESERVATIONS_PATH}/search/findByCurrentClient`, { params: httpParams });
  }

  getReservationsByRange(reservationsRangeDto: ReservationsRange): Observable<ReservationResponse[]> {
    const httpParams = {
      dateFrom: reservationsRangeDto.dateFrom,
      dateTo: reservationsRangeDto.dateTo,
      restaurantId: reservationsRangeDto.restaurantId
    };

    return this.http.get<ReservationResponse[]>(`/api/${this.RESERVATIONS_PATH}/search/findAllByDatesBetweenAndRestaurantId`,
      { params: httpParams })
      .pipe(
        map((response: any) => {
          const reservations = (response._embedded && response._embedded.reservationRequests) || [];

          return reservations;
        }),
        catchError((error: any) => {
          return of([]);
        }),
      );
  }
}
