import { Component, OnInit, Input } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss'],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
})
export class SideNavComponent implements OnInit {

  location: Location;

  geo = navigator.geolocation;
  lat;
  lng;

  @Input()
  isOpen;


  constructor(location: Location) { this.location = location; }

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
  }

  checkrad(radbutt) {
    return radbutt.checked;
  }

  check(object) {
    console.log(object);
    return object;
  }



}
