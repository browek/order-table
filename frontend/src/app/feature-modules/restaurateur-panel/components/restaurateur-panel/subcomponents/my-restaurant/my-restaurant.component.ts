import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {VenueWithDetails} from '../../../../../../shared/model/resturateur-venue-models';
import {RestaurateurService} from '../../../../../../shared/services/restaurateur.service';

@Component({
  selector: 'app-my-restaurant',
  templateUrl: './my-restaurant.component.html',
  styleUrls: ['./my-restaurant.component.scss']
})
export class MyRestaurantComponent implements OnInit {

  @Input() restaurantDetails: VenueWithDetails;
  @Output() onUnassign = new EventEmitter<any>();

  constructor(private restaurService: RestaurateurService) {
  }

  ngOnInit() {
    this.restaurService.getMyRestaurant().subscribe(res => this.restaurantDetails = res);
  }

  unassignRestaurant() {
    this.restaurService.unassignRestaurant().subscribe(() => this.onUnassign.emit());
  }

}
