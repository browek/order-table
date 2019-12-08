import { VenueDetails } from './../../../../shared/models/venue-details';
import { VenueService } from './../../../../shared/services/venue.service';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { DialogService } from '@app/shared/services';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  location: Location;
  latitude: number;
  longitude: number;

  geo = navigator.geolocation;
  customerLabel = 'Tu jesteś';
  customerMapIcon = 'assets/icons/customer-map-marker.svg';
  shopMapIcon = 'assets/icons/shop-map-marker.svg';
  registeredIcon = 'assets/icons/registered-map-maker.svg';
  arrVenues: VenueDetails[];
  isRegistered;

  userQuery = '';

  public isOpen = false;

  constructor(private venueService: VenueService, private dialogService: DialogService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.getLocation();

  }

  sidenavToggle() {
    this.isOpen = !this.isOpen;
  }

  findVenues() {
    this.venueService.getVenues({
      lat: this.latitude,
      lng: this.longitude,
      query: this.userQuery
    }).subscribe(response => {
      this.arrVenues = response;
      console.log(this.arrVenues);
    });
  }

  getLocation() {
    if (this.geo) {
      this.geo.getCurrentPosition((location) => {
        console.log('Szerokość ' + location.coords.latitude);
        console.log('Długość ' + location.coords.longitude);
        this.latitude = location.coords.latitude;
        this.longitude = location.coords.longitude;
      });
    } else {
      console.log('niedostępny');
    }
  }

  checkRegistered(isRegistered: boolean) {
    if (isRegistered === true) {
      return this.registeredIcon;
    } else {
      return this.shopMapIcon;

    }
  }

  reservationDialog() {
    this.dialogService.openReservationDialog();
  }

  check() {
    console.log(this.arrVenues);
  }
}
