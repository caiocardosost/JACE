import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

@Component({
  imports: [MdbFormsModule],
  selector: 'app-login-component',
  styleUrl: './login-component.scss',
  templateUrl: './login-component.html',
})
export class LoginComponent {

  router = inject(Router)
  entrar(){
    this.router.navigate(["app/paciente"]);

  }
}
