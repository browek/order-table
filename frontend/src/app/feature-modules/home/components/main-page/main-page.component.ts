import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Location } from '@angular/common';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  location: Location;

  geo = navigator.geolocation;
  lat;
  lng;
  customerLabel = 'Tu jesteś';
  customerMapIcon = 'assets/icons/customer-map-marker.svg';
  shopMapIcon = 'assets/icons/shop-map-marker.svg';
  arrVenues: string [];

  public isOpen = false;



  constructor(private httpService: HttpClient, location: Location) {
    this.location = location;
  }

  ngOnInit() {
    if (this.geo) {
      this.geo.getCurrentPosition((location) => {

        console.log('Szerokość ' + location.coords.latitude);
        console.log('Długość ' + location.coords.longitude);
        this.lat = location.coords.latitude;
        this.lng = location.coords.longitude;

      });
    } else {
      console.log('niedostępny');
    }
    this.httpService.get(environment.apiUrl+'/venues/search').subscribe(
      data => {
        this.arrVenues = data as string [];
        console.log(this.arrVenues);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  }

  sidenavToggle() {
    this.isOpen = !this.isOpen;
  }

  searchVenue(){
    this.httpService.get(environment.apiUrl+'/venues/search').subscribe(
      data => {
        this.arrVenues = data as string [];
        console.log(this.arrVenues);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
    
  }

}
