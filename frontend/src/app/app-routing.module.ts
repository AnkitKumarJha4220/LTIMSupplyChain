import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'supplylink',
    loadChildren: () => import('./supplylink/supplylink.module').then((m) => m.SupplyLinkModule),
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/auth',  // Redirect to 'auth' route by default
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
