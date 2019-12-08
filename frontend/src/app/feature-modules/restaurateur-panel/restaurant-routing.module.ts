import { RestaurateurPanelComponent } from './components/restaurateur-panel/restaurateur-panel.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ReservationRequestsComponent} from '@features/restaurateur-panel/components/reservation-requests/reservation-requests.component';

const routes: Routes = [
  { path: 'myrestaurant', pathMatch: 'full', component: RestaurateurPanelComponent },
  { path: 'reservations/questions', component: ReservationRequestsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
