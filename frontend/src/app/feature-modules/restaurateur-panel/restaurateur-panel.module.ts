import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SideNavLayoutModule, SharedModule, PageHeaderModule } from '@app/shared/modules';
import { RestaurateurService } from '@shared/services/restaurateur.service';
import { RestaurantRoutingModule } from './restaurant-routing.module';
import {
  RestaurateurLayoutComponent,
  AssignRestaurantComponent,
  ReservationRequestsComponent
} from './components';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { ReservationsComponent } from './components/reservations/reservations.component';

@NgModule({
  declarations: [
    RestaurateurLayoutComponent,
    AssignRestaurantComponent,
    ReservationRequestsComponent,
    RestaurantsComponent,
    ReservationsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    SideNavLayoutModule,
    RestaurantRoutingModule,
    PageHeaderModule
  ],
  providers: [RestaurateurService]
})
export class RestaurateurPanelModule { }
