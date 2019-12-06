import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {RestaurateurService} from '../../../../../../shared/services/restaurateur.service';
import {Venue, VenueWithDetails} from '../../../../../../shared/model/resturateur-venue-models';

@Component({
  selector: 'app-assign-restaurant',
  templateUrl: './assign-restaurant.component.html',
  styleUrls: ['./assign-restaurant.component.scss']
})
export class AssignRestaurantComponent implements OnInit {

  @Output() onRestaurantAssigned: EventEmitter<VenueWithDetails> = new EventEmitter();


  firstFormGroup: FormGroup;

  foundVenues: Venue[] = [];

  selectedRestaurant = <Venue>({});

  listIsLoading = true;
  listIsEmpty = false;

  constructor(private restaurService: RestaurateurService, private _formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      restaurantName: ['', [Validators.required, Validators.min(3), Validators.max(32)]],
      city: ['', [Validators.required, Validators.min(3), Validators.max(32)]]
    });
  }

  searchForRestaurants() {
    this.foundVenues = [];
    this.listIsLoading = true;
    this.listIsEmpty = false;

    this.restaurService.searchRestaurants(
      this.firstFormGroup.controls['restaurantName'].value,
      this.firstFormGroup.controls['city'].value
    ).subscribe(venues => {
        this.foundVenues = venues;
        this.listIsLoading = false;
        this.listIsEmpty = false;

        if (this.foundVenues.length === 0) {
          this.listIsEmpty = true;
      }
    }, error => {

        this.listIsEmpty = true;
        this.listIsLoading = false;
    });
  }

  assign() {
    this.restaurService
      .assignRestaurant(this.selectedRestaurant.foursquareId)
      .subscribe(venueDetails => {
        console.log(venueDetails);
        this.onRestaurantAssigned.emit(venueDetails);
      });
  }

}
