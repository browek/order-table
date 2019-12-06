import {Component, EventEmitter, OnInit} from '@angular/core';
import {RestaurateurService} from '../../../../shared/services/restaurateur.service';
import {VenueWithDetails} from '../../../../shared/model/resturateur-venue-models';
import {HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-restaurateur-panel',
  templateUrl: './restaurateur-panel.component.html',
  styleUrls: ['./restaurateur-panel.component.scss']
})
export class RestaurateurPanelComponent implements OnInit {

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
    this.restaurService.getMyRestaurant().subscribe(venue => {
      this.myRestaurant = venue;
      this.contentLoaded = true;
      this.noAssignedRestaurant = false;
    }, (error: HttpResponse<any>) => {
      if (error.status === 404) {
        this.contentLoaded = true;
        this.noAssignedRestaurant = true;
      } else {
        this.unknownError = true;
      }
    });
  }

}
