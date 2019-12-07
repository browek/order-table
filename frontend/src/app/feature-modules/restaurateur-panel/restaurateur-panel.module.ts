import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RestaurateurService} from '../../shared/services/restaurateur.service';
import { RestaurateurPanelComponent } from './components/restaurateur-panel/restaurateur-panel.component';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule, MatListModule,
  MatProgressSpinnerModule,
  MatStepperModule
} from '@angular/material';
import { MyRestaurantComponent } from './components/restaurateur-panel/subcomponents/my-restaurant/my-restaurant.component';
import { AssignRestaurantComponent } from './components/restaurateur-panel/subcomponents/assign-restaurant/assign-restaurant.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RestaurantRoutingModule } from './restaurant-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatStepperModule,
    MatListModule,
    RestaurantRoutingModule
  ],
  declarations: [RestaurateurPanelComponent, MyRestaurantComponent, AssignRestaurantComponent],
  providers: [RestaurateurService],
  exports: [RestaurateurPanelComponent]
})
export class RestaurateurPanelModule { }
