import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/modules';
import { AdminPanelRoutingModule } from './admin-panel-routing.module';
import { AdminLayoutComponent } from './components/layout/admin-layout.component';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [AdminLayoutComponent],
  imports: [
    SharedModule,
    AdminPanelRoutingModule
  ],
  providers: [
    UserService
  ]
})
export class AdminPanelModule { }
