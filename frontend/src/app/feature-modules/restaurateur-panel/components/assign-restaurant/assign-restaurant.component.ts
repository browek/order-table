import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Venue, RestaurantToSearch, VenueWithDetails } from '@app/shared/models';
import { RestaurateurService } from '@app/shared/services/restaurateur.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-assign-restaurant',
  templateUrl: './assign-restaurant.component.html',
  styleUrls: ['./assign-restaurant.component.scss']
})
export class AssignRestaurantComponent implements OnInit {

  firstFormGroup: FormGroup;

  foundVenues: Venue[] = [];

  selectedRestaurant: Venue;

  listIsLoading = true;
  listIsEmpty = false;

  constructor(
    private restaurService: RestaurateurService,
    private _formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.listIsLoading = true;
    this.listIsEmpty = false;
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      restaurantName: ['', [Validators.required, Validators.min(3), Validators.max(32)]],
      city: ['', [Validators.required, Validators.min(3), Validators.max(32)]]
    });
  }

  searchForRestaurants() {
    this.foundVenues = [];

    const formControls = this.firstFormGroup.controls;
    const restaurantToSearch: RestaurantToSearch = {
      city: formControls['city'].value,
      restaurantName: formControls['restaurantName'].value
    };

    this.restaurService.searchRestaurants(restaurantToSearch)
      .subscribe(this.handleSuccessfulSearch, this.handleErrorSearch);
  }

  handleSuccessfulSearch = (venues: Venue[]) => {
    this.foundVenues = venues;
    this.listIsLoading = false;
    this.listIsEmpty = ! (!!this.foundVenues.length);
  }

  handleErrorSearch = (error: HttpErrorResponse) => {
    this.listIsEmpty = true;
    this.listIsLoading = false;
  }

  assign() {
    this.restaurService
      .assignRestaurant(this.selectedRestaurant.foursquareId)
      .subscribe((venueDetails: VenueWithDetails) => {
        this.toastr.success(this.getSuccessMessageAfterAssign(venueDetails), 'Sukces!');
        this.router.navigateByUrl('/restaurant-panel/restaurants');
      });
  }

  private getSuccessMessageAfterAssign(venueDetails: VenueWithDetails): string {
    const assignedRestaurant = `Dodano restauracje ${venueDetails.name}`;
    const restaurantWithCityMessage = assignedRestaurant + (
      venueDetails.location ? `, ${venueDetails.location.city}` : ''
    );

    return restaurantWithCityMessage;
  }
}
