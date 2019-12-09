import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReservationResponse, ReservationStatus } from '@app/shared/models/reservation-response';
import { ReservationService } from '@app/shared/services/reservation.service';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent implements OnInit {

  constructor (private reservationService: ReservationService) { }

  arrReservations: ReservationResponse [];

  ngOnInit () {
    this.fetchClientReservations();
  }

  fetchClientReservations() {
    this.reservationService.getClientReservations()
      .subscribe((response: any) => {
        this.arrReservations = response._embedded.reservationRequests || [];
      });
  }


  getReservationStatusMessage(status: ReservationStatus): ReservationStatusMessage {
    switch (status) {
      case ReservationStatus.ACCEPTED_BY_RESTAURANT:
        return {
          message: 'Zaakceptowana',
          class: 'badge-success'
        };

      case ReservationStatus.REJECTED_BY_RESTAURANT:
        return {
          message: 'Odrzucona',
          class: 'badge-danger'
        };

      case ReservationStatus.SEND:
        return {
          message: 'Oczekująca',
          class: 'badge-info'
        };
    }
  }
}

interface ReservationStatusMessage {
  message: string;
  class: string;
}
