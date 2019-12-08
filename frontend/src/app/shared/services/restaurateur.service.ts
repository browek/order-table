import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venue, VenueWithDetails } from '../model/resturateur-venue-models';

@Injectable({
  providedIn: 'root'
})
export class RestaurateurService {

  constructor(private http: HttpClient) {
  }

  searchRestaurants(restaurantName: string, city: string): Observable<Venue[]> {
    return this.http.get<Venue[]>(`/api/restaurants/search`,
      {
        params: new HttpParams().set('restaurant_name', restaurantName).set('city', city)
      });
  }

  assignRestaurant(restaurantVenueId: string): Observable<VenueWithDetails> {
    return this.http.put<VenueWithDetails>(`/api/restaurants/assign`, null, {
      params: new HttpParams().set('foursquare_id', restaurantVenueId)
    });
  }

  unassignRestaurant(): Observable<void> {
    return this.http.put<void>(`/api/restaurants/unassign`, null);
  }
}
