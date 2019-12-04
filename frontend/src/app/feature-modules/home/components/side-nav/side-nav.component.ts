import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss']
})
export class SideNavComponent implements OnInit {

  location: Location;

  geo = navigator.geolocation;
  latitude;
  longitude;

  @Input()
  isOpen;

  constructor(location: Location) {
    this.location = location;
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

  checkrad(radbutt) {
    return radbutt.checked;
  }

  check(object) {
    console.log(object);
    return object;
  }

}
