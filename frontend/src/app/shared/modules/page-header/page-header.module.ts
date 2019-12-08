import { NgModule } from '@angular/core';
import { SharedModule } from '../shared.module';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [HeaderComponent],
  imports: [
    SharedModule,
    RouterModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class PageHeaderModule { }
