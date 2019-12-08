import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
  MainPageComponent,
  UserReservationsComponent,
  LayoutComponent
} from './components';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'map'
      },
      {
        path: 'map',
        component: MainPageComponent,
      },
      {
        path: 'reservations',
        component: UserReservationsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
