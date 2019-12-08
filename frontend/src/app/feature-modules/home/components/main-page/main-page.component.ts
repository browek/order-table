import { AuthService } from '@app/core/services';
import { AgmMap } from '@agm/core';
import { Component, OnDestroy, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { SidenavService } from '@app/shared/modules/side-nav-layout/services';
import { DialogService, VenueService } from '@app/shared/services';
import { GoogleLocation } from '@shared/models/google-location';
import { VenueDetails } from '@shared/models/venue-details';
import { GeoLocationService } from '@shared/services/geo-location.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class MainPageComponent implements OnInit, OnDestroy {

  selectedUserLocation: GoogleLocation = {
    lat: 0,
    lng: 0,
    zoom: 6
  };

  @ViewChild(AgmMap) map: AgmMap;

  geo = navigator.geolocation;
  customerLabel = 'Tu jesteś';
  customerMapIcon = 'assets/icons/customer-map-marker.svg';
  shopMapIcon = 'assets/icons/shop-map-marker.svg';
  registeredIcon = 'assets/icons/registered-map-maker.svg';
  arrVenues: VenueDetails[];
  isRegistered: boolean;

  subscription: Subscription;

  userQuery = '';

  private sidenavOpened = false;

  constructor(private venueService: VenueService, private dialogService: DialogService,
    private geoLocationService: GeoLocationService, private authService: AuthService,
    private sidenavService: SidenavService
  ) {
  }

  ngOnInit() {
    this.initUserLocation();
    this.subscription = this.subscribeUserSelectedLocation();
  }

  private subscribeUserSelectedLocation(): Subscription {
    return this.sidenavService.selectedLocation
      .subscribe(this.setSelectedLocation);
  }

  private setSelectedLocation = (location: GoogleLocation) => {
    if (location) {
      this.selectedUserLocation = {
        lat: location.lat,
        lng: location.lng,
        zoom: 15
      };
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  sidenavToggle() {
    this.sidenavOpened = !this.sidenavOpened;
    this.sidenavService.setSidenavOpened(this.sidenavOpened);
  }

  findVenues() {
    this.venueService.getVenues({
      lat: this.selectedUserLocation.lat,
      lng: this.selectedUserLocation.lng,
      query: this.userQuery
    }).subscribe(response => {
      this.arrVenues = response;
    });
  }

  private initUserLocation() {
    this.geoLocationService.getCurrentPosition()
      .subscribe((position: Position) => {
        this.selectedUserLocation.lat = position.coords.latitude;
        this.selectedUserLocation.lng = position.coords.longitude;
      });
  }

  getIcon(isRegistered: boolean) {
    if (isRegistered === true) {
      return this.registeredIcon;
    } else {
      return this.shopMapIcon;
    }
  }

  getMarkerLabel(name: string) {
    return {
      color: 'red',
      text: name
    };
  }

  reservationDialog(venueId: string) {
    this.dialogService.openReservationDialog(venueId);
  }

  isLogged(): boolean {
    return this.authService.isLogged();
  }

}
