import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestaurantToSearch, Venue, VenueWithDetails } from '../models';

@Injectable()
export class RestaurateurService {

  private readonly ACTIVATED_RESTAURANTS_PATH = '/api/activatedRestaurants';
  private readonly RESTAURANTS_PATH = '/api/restaurants';

  private readonly RESERVATIONS_PATH = '/api/reservationRequests';

  constructor(private http: HttpClient) {
  }

  searchRestaurants(restaurantToSearch: RestaurantToSearch): Observable<Venue[]> {
    const params = {
      restaurant_name: restaurantToSearch.restaurantName,
      city: restaurantToSearch.city
    };

    return this.http.get<Venue[]>(`${this.RESTAURANTS_PATH}/search`, { params });
  }

  assignRestaurant(restaurantVenueId: string): Observable<VenueWithDetails> {
    const params = {
      foursquare_id: restaurantVenueId
    };

    return this.http.put<VenueWithDetails>(`${this.RESTAURANTS_PATH}/assign`, null, { params });
  }

  unassignRestaurant(): Observable<void> {
    return this.http.put<void>(`${this.RESTAURANTS_PATH}/unassign`, null);
  }

  getRestaurants(httpParams?: HttpParams | any): Observable<any> {
    return this.http.get(`${this.ACTIVATED_RESTAURANTS_PATH}/search/findAllByCurrentUser`, { params: httpParams });
  }

  getReservationRequests(restaurantId: number) {
    const params = new HttpParams()
      .set('restaurantId', `${restaurantId}`)
      .set('status', 'SEND')
      .set('projection', 'withUsername');
    return this.http.get(`${this.RESERVATIONS_PATH}/search/findByStatusAndId`, { params: params});
  }

  acceptReservation(id: number, message: string) {
    return this.http.put(`${this.RESTAURANTS_PATH}/reservations/accept`, {
      reservationId: id,
      message: message
    });
  }

  rejectReservation(id: number, message: string) {
    return this.http.put(`${this.RESTAURANTS_PATH}/reservations/reject`, {
      reservationId: id,
      message: message
    });
  }
}
