import {Component, OnInit} from '@angular/core';
import {ReservationRequest} from '@shared/models';

@Component({
  selector: 'app-reservation-requests',
  templateUrl: './reservation-requests.component.html',
  styleUrls: ['./reservation-requests.component.scss']
})
export class ReservationRequestsComponent implements OnInit {

  reservationRequests: ReservationRequest[] =
    [
      {
        id: 1,
        dateAndTime: new Date(),
        numberOfPersons: 5,
        message: 'Hallo'
      },
      {
        id: 2,
        dateAndTime: new Date(),
        numberOfPersons: 5,
      },
      {
        id: 3,
        dateAndTime: new Date(),
        numberOfPersons: 5,
        message: 'Hallo'
      },
      {
        id: 4,
        dateAndTime: new Date(),
        numberOfPersons: 5,
        message: 'Hallo'
      }
    ];

  constructor() {
  }

  ngOnInit() {
  }

}
