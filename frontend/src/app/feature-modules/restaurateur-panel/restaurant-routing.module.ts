import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
  RestaurateurLayoutComponent,
  ReservationRequestsComponent,
  AssignRestaurantComponent,
  RestaurantsComponent
} from './components';

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
        path: 'reservations',
        component: ReservationRequestsComponent,
      },
      {
        path: 'assign-restaurant',
        component: AssignRestaurantComponent
      },
      {
        path: 'restaurants',
        component: RestaurantsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
