import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavBarComponent } from './navbar/navbar.component';
import { AuthModule } from '../auth/auth.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    NavBarComponent 
  ],
  imports: [
    CommonModule,
    AuthModule,
    RouterModule
  ],
  exports: [
    NavBarComponent
  ]
})
export class SharedModule {}