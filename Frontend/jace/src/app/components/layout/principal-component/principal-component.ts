import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from '../menu-component/menu-component';

@Component({
  imports: [RouterOutlet, MenuComponent],
  selector: 'app-principal-component',
  styleUrl: './principal-component.scss',
  templateUrl: './principal-component.html',
})
export class PrincipalComponent {}
