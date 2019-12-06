import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { faSearch, faWrench } from '@fortawesome/free-solid-svg-icons';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  // @Input()
  // homeSidenav;

  @Output()
  homeSidenav = new EventEmitter<Boolean>();

  location: Location;

  geo = navigator.geolocation;
  lat;
  lng;
  customerLabel = 'Tu jesteś';
  // tslint:disable-next-line:max-line-length
  customerMapIcon = 'assets/icons/customer-map-marker.svg';
  shopMapIcon = 'assets/icons/shop-map-marker.svg';
  arrVenues: string [];


  backgroundImg = [
    '/assets/background/1.jpg',
    '/assets/background/2.jpg',
    '/assets/background/3.jpg',
    '/assets/background/4.jpg'
  ];
  randomNumber = Math.floor(Math.random() * this.backgroundImg.length);
  bgImg = this.backgroundImg[this.randomNumber];


  faSearch = faSearch;
  faWrench = faWrench;

  constructor(location: Location, private httpService: HttpClient) { this.location = location; }

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
    this.httpService.get('./assets/venues.json').subscribe(
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
    this.homeSidenav.emit();
  }

}
