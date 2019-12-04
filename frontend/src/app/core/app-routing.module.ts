import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: '@features/home/home.module#HomeModule'
  },
  // {
  //   path: 'admin-panel',
  //   loadChildren: '@features/admin-panel/admin-panel.module#AdminPanelModule'
  // } // TODO
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
