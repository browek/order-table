import { NgModule } from '@angular/core';
import { SharedModule } from '../shared.module';
import { LayoutComponent } from './components';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [LayoutComponent],
  imports: [
    SharedModule,
    RouterModule
  ],
  exports: [
    LayoutComponent
  ]
})
export class SideNavLayoutModule { }
