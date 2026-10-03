import { Component } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';

@Component({
  imports: [MdbCollapseModule],
  selector: 'app-menu-component',
  styleUrl: './menu-component.scss',
  templateUrl: './menu-component.html',
})
export class MenuComponent {}
