import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PageHeaderModule, SharedModule, SideNavLayoutModule } from '@app/shared/modules';
import { RestaurateurService } from '@shared/services/restaurateur.service';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AssignRestaurantComponent, ReservationRequestsComponent, RestaurateurLayoutComponent } from './components';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { RestaurantsComponent } from './components/restaurants/restaurants.component';
import { RestaurantRoutingModule } from './restaurant-routing.module';

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
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    SharedModule,
    SideNavLayoutModule,
    RestaurantRoutingModule,
    PageHeaderModule
  ],
  providers: [RestaurateurService]
})
export class RestaurateurPanelModule { }
