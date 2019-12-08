import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venue, VenueWithDetails, RestaurantToSearch } from '../model';

@Injectable({
  providedIn: 'root'
})
export class RestaurateurService {

  constructor(private http: HttpClient) {
  }

  searchRestaurants(restaurantToSearch: RestaurantToSearch): Observable<Venue[]> {
    const params = {
      restaurant_name: restaurantToSearch.restaurantName,
      city: restaurantToSearch.city
    };

    return this.http.get<Venue[]>(`/api/restaurants/search`, { params });
  }

  assignRestaurant(restaurantVenueId: string): Observable<VenueWithDetails> {
    const params = {
      foursquare_id: restaurantVenueId
    };

    return this.http.put<VenueWithDetails>(`/api/restaurants/assign`, null, { params });
  }

  unassignRestaurant(): Observable<void> {
    return this.http.put<void>(`/api/restaurants/unassign`, null);
  }
}
