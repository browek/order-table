import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurateurLayoutComponent, ReservationRequestsComponent, AssignRestaurantComponent } from './components';

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
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
