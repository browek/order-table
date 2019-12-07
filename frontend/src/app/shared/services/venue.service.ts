import { VenueDetails } from './../models/venue-details';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class VenueService {

  constructor(private http: HttpClient) {
  }

  getVenues(venueDetails: VenueDetails): Observable<any[]> {
    return this.http.post<any[]>('/api/venues/search', venueDetails);
  }
}
