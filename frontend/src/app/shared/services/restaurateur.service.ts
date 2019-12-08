import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venue, VenueWithDetails, RestaurantToSearch } from '../models';

@Injectable()
export class RestaurateurService {

  private readonly RESTAURANTS_PATH = '/api/restaurants';

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

  getRestaurants(httpParams: HttpParams | any) {
    return this.http.get(`${this.RESTAURANTS_PATH}`, { params: httpParams });
  }
}
