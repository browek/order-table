import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent implements OnInit {


  constructor (private httpService: HttpClient) { }

  arrReservations: string [];

  ngOnInit () {
    this.httpService.get('./assets/json/reservations.json').subscribe(
      data => {
        this.arrReservations = data as string [];
          console.log(this.arrReservations);
      },
      (err: HttpErrorResponse) => {
        console.log (err.message);
      }
    );
  }

}
