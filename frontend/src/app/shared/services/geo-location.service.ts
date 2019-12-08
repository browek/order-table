import { MapsAPILoader } from '@agm/core';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeoLocationService {

  private geoCoder: any;

  constructor(private mapsApiLoader: MapsAPILoader, private toastr: ToastrService) {
    this.mapsApiLoader.load().then(() => {
      this.geoCoder = new google.maps.Geocoder();
    });
  }

  public getCurrentPosition = (): Observable<Position> => {
    return Observable.create((observer: Observer<Position>) => {
      navigator.geolocation.getCurrentPosition((pos: Position) => {
        observer.next(pos);
        observer.complete();
      },
        this.handleError,
        {
          enableHighAccuracy: true
        }
      );
    });
  }

  private handleError = (error: any) => {
    this.toastr.error(error.message, 'Błąd!');
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
