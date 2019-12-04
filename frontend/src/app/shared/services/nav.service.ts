import { SideNavComponent } from './../../side-nav/side-nav.component';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavService {

  SideNavComponent = SideNavComponent;

  constructor() { }



}


