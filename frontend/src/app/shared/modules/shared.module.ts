import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';

import {
  MatMenuModule,
  MatButtonModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatSidenavModule,
  MatButtonToggleModule,
  MatRadioModule,
  MatTableModule,
  MatGridListModule,
  MatToolbarModule,
  MatListModule,
  MatProgressSpinnerModule,
  MatStepperModule,
  MatCardModule,
  MatPaginatorModule,
  MatSortModule,
  MatAutocompleteModule,
  MatTooltipModule,
  MatSelectModule
} from '@angular/material';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';

import {
  faSearch,
  faWrench
} from '@fortawesome/free-solid-svg-icons';

// Add an icon to the library for convenient access in other components
library.add(
  faSearch,
  faWrench
);

@NgModule({
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatButtonToggleModule,
    MatRadioModule,
    MatTableModule,
    MatGridListModule,
    MatProgressSpinnerModule,
    MatStepperModule,
    MatCardModule,
    MatPaginatorModule,
    MatSortModule,
    MatAutocompleteModule,
    MatTooltipModule,
    MatSelectModule,
    FontAwesomeModule
  ]
})
export class SharedModule { }
