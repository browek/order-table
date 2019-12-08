import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SideNavLayoutModule, SharedModule } from '@app/shared/modules';
import { RestaurateurService } from '@shared/services/restaurateur.service';
import { RestaurantRoutingModule } from './restaurant-routing.module';
import {
  RestaurateurLayoutComponent,
  AssignRestaurantComponent,
  ReservationRequestsComponent
} from './components';

@NgModule({
  declarations: [
    RestaurateurLayoutComponent,
    AssignRestaurantComponent,
    ReservationRequestsComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    SideNavLayoutModule,
    RestaurantRoutingModule
  ],
  providers: [RestaurateurService],
  exports: []
})
export class RestaurateurPanelModule { }
