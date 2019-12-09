import { Component, OnInit } from '@angular/core';
import { ReservationRequest } from '@shared/models';
import { Restaurant } from '@features/restaurateur-panel/models';
import { RestaurateurService } from '@shared/services';
import { ToastrService } from 'ngx-toastr';

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

  constructor(private restaurateurService: RestaurateurService, private toastr: ToastrService) {
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

  selectRestaurant = (event: any) => {
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
  }

  acceptReservation = (id: number, message: string): void => {
    this.restaurateurService.acceptReservation(id, message)
      .subscribe(res => {
        this.removeReservationById(id);
        this.toastr.info('Potwierdzono rezerwacje użytkownika');
      });
  }

  rejectReservation = (id: number, message: string) => {
    this.restaurateurService.rejectReservation(id, message)
      .subscribe(res => {
        this.removeReservationById(id);
        this.toastr.info('Odrzucono rezerwacje użytkownika');
      });
  }

  private removeReservationById(id: number): void {
    this.reservationRequests = this.reservationRequests.filter(request => request.id !== id);
  }
}
