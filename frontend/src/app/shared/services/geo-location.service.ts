import { MapsAPILoader } from '@agm/core';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeoLocationService {

  private geoCoder: any;

  constructor(private mapsApiLoader: MapsAPILoader) {
    this.mapsApiLoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder();
    });
  }

  public getCurrentPosition = (): Observable<Position> => {
    return Observable.create((observer: Observer<Position>) => {
      navigator.geolocation.watchPosition((pos: Position) => {
        observer.next(pos);
      },
        error => { console.error(error); },
        {
          enableHighAccuracy: true
        }
      );
    });
  }

  public getPositions = (address: string): Observable<google.maps.GeocoderResult[]> => {
    return Observable.create((observer: Observer<Position>) => {
      this.geoCoder.geocode({ 'address': address }, function (results, status) {
        observer.next(results);
        observer.complete();
      });
    });
  }
}
