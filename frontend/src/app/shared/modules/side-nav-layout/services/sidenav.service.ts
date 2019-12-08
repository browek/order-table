import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

declare var google: any;

@Injectable({
  providedIn: 'root'
})
export class SidenavService {

  private resource = new Subject<boolean>();
  sidenavOpened = this.resource.asObservable();

  private locationResource = new Subject<google.maps.GeocoderResult>();
  selectedLocation = this.locationResource.asObservable();

  setSidenavOpened(opened: boolean) {
    this.resource.next(opened);
  }

  setSelectedLocation(location: google.maps.GeocoderResult) {
    this.locationResource.next(location);
  }
}
