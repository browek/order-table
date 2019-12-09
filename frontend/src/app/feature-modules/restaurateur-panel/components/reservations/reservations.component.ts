import { RestaurateurService } from '@shared/services/restaurateur.service';
import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent implements OnInit {

  restaurants: Restaurant[] = [];

  restaurantId: number;

  constructor(private restaurantService: RestaurateurService) { }

  ngOnInit() {
    this.fetchRestaurants();
  }

  fetchRestaurants = () => {
    this.restaurantService.getRestaurants()
      .subscribe((response: any) => {
        this.restaurants = response._embedded.activatedRestaurants || [];
      });
  }
}
