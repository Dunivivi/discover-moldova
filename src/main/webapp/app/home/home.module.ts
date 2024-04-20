import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { BottomActionsComponent } from '../shared/bottom-actions/bottom-actions.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild([HOME_ROUTE]), BottomActionsComponent],
  declarations: [HomeComponent],
})
export class HomeModule {}
