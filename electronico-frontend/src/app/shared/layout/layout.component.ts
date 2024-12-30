import { Component } from '@angular/core';
import { LayoutModule } from '../../modules/layout.module';

@Component({
  selector: 'app-layout',
  imports: [LayoutModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  Sidenav: boolean = false;
  showCustomer: boolean = false;
  showOrder: boolean = false;
  isExpanded = true;
  isShowing = false;
}
