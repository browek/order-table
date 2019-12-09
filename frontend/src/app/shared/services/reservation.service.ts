import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  getReservationFromRestaurant(reservationsRangeDto: ReservationsRangeDto): Observable<any> {
    const httpParams = {
      dateFrom: reservationsRangeDto.dateFrom,
      dateTo: reservationsRangeDto.dateTo,
      restaurantId: reservationsRangeDto.restaurantId
    };

    return this.http.get(`/api/${this.RESERVATIONS_PATH}/search/findAllByDateFromAndDateToAndRestaurantId`,
      { params: httpParams });
  }
}

interface ReservationsRangeDto {
  dateFrom: string;
  dateTo: string;
  restaurantId: string;
}
