import { DialogService } from '@shared/services/dialog.service';
import { AgmCoreModule } from '@agm/core';
import { NgModule } from '@angular/core';
import {
  NavBarComponent,
  LoginDialogComponent,
  RegisterDialogComponent,
  MainPageComponent,
  SideNavComponent
} from './components';
import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from '@shared/modules/shared.module';
import { CompareValidatorDirective } from '@shared/validators/compare-validator.directive';

@NgModule({
  declarations: [
    NavBarComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    MainPageComponent,
    SideNavComponent,
    CompareValidatorDirective
  ],
  imports: [
    SharedModule,
    HomeRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDFhBAj941EfWxzxb6R7EGXnRLflnnvtyo'
    })
  ],
  providers: [
    DialogService
  ],
  entryComponents: [
    LoginDialogComponent,
    RegisterDialogComponent,
    SideNavComponent
  ]
})
export class HomeModule { }
