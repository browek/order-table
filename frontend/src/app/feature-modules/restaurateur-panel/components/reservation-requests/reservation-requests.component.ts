import {Component, OnInit} from '@angular/core';
import {ReservationRequest} from '@shared/models';
import {Restaurant} from '@features/restaurateur-panel/models';
import {RestaurateurService} from '@shared/services';

@Component({
  selector: 'app-reservation-requests',
  templateUrl: './reservation-requests.component.html',
  styleUrls: ['./reservation-requests.component.scss']
})
export class ReservationRequestsComponent implements OnInit {

  restaurantsLoading = true;
  restaurantsLoadingError = false;

  reservationRequestsLoading = false;
  reservationRequestsLoadingError = false;

  selectedRestaurantId: number;

  restaurants: Restaurant[] = [];
  reservationRequests: ReservationRequest[] = [];

  constructor(private restaurateurService: RestaurateurService) {
  }

  ngOnInit() {
    this.restaurateurService.getRestaurants()
      .subscribe((restaurants: any) => {
        this.restaurants = restaurants._embedded.restaurants;

        this.restaurantsLoading = false;
        this.restaurantsLoadingError = false;
      }, err => {
        this.restaurantsLoading = false;
        this.restaurantsLoadingError = true;
      });


  }

  selectRestaurant = (event) => {
    this.reservationRequestsLoading = true;
    this.selectedRestaurantId = event.value;
    this.restaurateurService
      .getReservationRequests(this.selectedRestaurantId)
      .subscribe((reservations: any) => {
        this.reservationRequests = reservations;

        this.reservationRequestsLoading = false;
        this.reservationRequestsLoadingError = false;
      }, err => {
        this.reservationRequestsLoading = false;
        this.reservationRequestsLoadingError = true;
      });
  };

  acceptReservation = id => {
    this.restaurateurService.acceptReservation(id)
      .subscribe(res => {
        this.removeReservationById(id);
        console.log('accepted');
      });
  };

  private removeReservationById(id) {
    this.reservationRequests = this.reservationRequests.filter(request => request.id !== id);
  }

  rejectReservation = id => {
    this.restaurateurService.rejectReservation(id)
      .subscribe(res => {
        this.removeReservationById(id);
        console.log('rejected');
      });
  };

}
