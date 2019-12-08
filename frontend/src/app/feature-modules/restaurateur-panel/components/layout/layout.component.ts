import { Component, EventEmitter, OnInit } from '@angular/core';
import { VenueWithDetails } from '@app/shared/models';
import { RestaurateurService } from '@shared/services/restaurateur.service';

@Component({
  selector: 'app-restaurateur-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class RestaurateurLayoutComponent implements OnInit {

  onRestaurantAssignedEvent = new EventEmitter<VenueWithDetails>();

  contentLoaded = false;
  noAssignedRestaurant = false;
  unknownError = false;

  myRestaurant: VenueWithDetails;

  constructor(private restaurService: RestaurateurService) { }

  onRestaurantAssigned(venue) {
    this.noAssignedRestaurant = false;
  }

  onRestaurantUnassigned() {
    this.noAssignedRestaurant = true;
  }

  ngOnInit() {
    // this.restaurService.getMyRestaurant().subscribe(venue => {
    //   this.myRestaurant = venue;
    //   this.contentLoaded = true;
    //   this.noAssignedRestaurant = false;
    // }, (error: HttpResponse<any>) => {
    //   if (error.status === 404) {
    //     this.contentLoaded = true;
    //     this.noAssignedRestaurant = true;
    //   } else {
    //     this.unknownError = true;
    //   }
    // });
  }

}
