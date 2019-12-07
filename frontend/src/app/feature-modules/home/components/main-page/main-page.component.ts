import { VenueDetails } from './../../../../shared/models/venue-details';
import { VenueService } from './../../../../shared/services/venue.service';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

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
  arrVenues: VenueDetails[];

  userQuery = '';

  public isOpen = false;

  constructor(private venueService: VenueService) {
  }

  ngOnInit() {
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
    });
  }

}
