import { Component } from '@angular/core';
import { SharedModule } from '../shared.module';
import { SharedLibsModule } from '../shared-libs.module';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'jhi-bottom-actions',
  templateUrl: './bottom-actions.component.html',
  standalone: true,
  imports: [SharedModule, SharedLibsModule, RouterLinkActive, RouterLink]
})
export class BottomActionsComponent  {

}
