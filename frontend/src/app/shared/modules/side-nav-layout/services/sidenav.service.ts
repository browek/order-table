import { Injectable } from '@angular/core';
import { GoogleLocation } from '@shared/models/google-location';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SidenavService {

  private resource = new Subject<boolean>();
  sidenavOpened = this.resource.asObservable();

  private locationResource = new Subject<GoogleLocation>();
  selectedLocation = this.locationResource.asObservable();

  setSidenavOpened(opened: boolean) {
    this.resource.next(opened);
  }

  setSelectedLocation(location: GoogleLocation) {
    this.locationResource.next(location);
  }
}
