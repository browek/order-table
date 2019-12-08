import { MapsAPILoader, GoogleMapsAPIWrapper } from '@agm/core';
import { Component, Input, OnInit, NgZone } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SidenavService } from '@app/shared/modules/side-nav-layout/services';
import { GoogleLocation } from '@shared/models/google-location';
import { GeoLocationService } from '@shared/services/geo-location.service';
import { from, Observable, of, Observer } from 'rxjs';
import { catchError, delay, map, startWith, switchMap, tap } from 'rxjs/operators';
import { MatAutocompleteSelectedEvent } from '@angular/material';

declare var google: any;

@Component({
  selector: 'app-home-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  userLocation: GoogleLocation = {
    lat: null,
    lng: null,
    zoom: 6
  };

  @Input()
  isOpen: boolean;
  public geoCoder: any;

  selectedLocationControl = new FormControl();
  locations: Observable<any[]>;

  searchedLocations: any[];

  constructor(
    private geoLocationService: GeoLocationService,
    private mapsApiLoader: MapsAPILoader,
    private sideNavService: SidenavService) {

    this.mapsApiLoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder();
    });
  }

  ngOnInit() {
    this.initUserLocation();
    this.locations = this.handleSelectedLocationChange();
  }

  private handleSelectedLocationChange = (): Observable<any[]> => {
    return this.selectedLocationControl.valueChanges
      .pipe(
        delay(50),
        startWith(''),
        switchMap((address: string) => {
          return this.geoLocationService.getPositions(address);
        }),
        map((data: any) => {
          return data && data;
        }),
        catchError((error: any) => {
          return of([]);
        }),
        tap((data: any[]) => {
          this.searchedLocations = data;
        })
      );
  }

  setUserLocation($event: MatAutocompleteSelectedEvent) {
    const location: string = $event.option.value;
    const index = this.searchedLocations.findIndex(elem => elem.formatted_address === location);

    if (index !== -1) {
      this.sideNavService.setSelectedLocation(this.searchedLocations[index]);
    }
  }

  private initUserLocation() {
    this.geoLocationService.getCurrentPosition()
      .subscribe((position: Position) => {
        this.userLocation.lat = position.coords.latitude;
        this.userLocation.lng = position.coords.longitude;
      });
  }
}
