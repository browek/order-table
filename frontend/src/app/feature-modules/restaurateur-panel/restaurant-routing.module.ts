import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
  RestaurateurLayoutComponent,
  ReservationRequestsComponent,
  AssignRestaurantComponent,
  RestaurantsComponent
} from './components';
import {ReservationsComponent} from '@features/restaurateur-panel/components/reservations/reservations.component';

const routes: Routes = [
  {
    path: '',
    component: RestaurateurLayoutComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'reservations'
      },
      {
        path: 'reservationRequests',
        component: ReservationRequestsComponent,
      },
      {
        path: 'assign-restaurant',
        component: AssignRestaurantComponent
      },
      {
        path: 'restaurants',
        component: RestaurantsComponent
      },
      {
        path: 'reservations',
        component: ReservationsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
