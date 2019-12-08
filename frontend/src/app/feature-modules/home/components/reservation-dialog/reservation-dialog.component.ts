import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reservation-dialog',
  templateUrl: './reservation-dialog.component.html',
  styleUrls: ['./reservation-dialog.component.scss']
})
export class ReservationDialogComponent implements OnInit {

  public reservationForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.reservationForm = new FormGroup({
      quantity: new FormControl(null, [Validators.required]),
      date: new FormControl(null, [Validators.required]),
    });
  }

}
