import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-login-component',
  styleUrl: './login-component.scss',
  templateUrl: './login-component.html',
})
export class LoginComponent {
  private router = inject(Router);

  // Marca exibida na tela. Para vender a clientes, basta trocar aqui
  // (ou, no futuro, carregar de uma configuração).
  marca = {
    nome: 'Jace',
    slogan: 'Motor de Gestão da Jornada Clínica',
  };

  mostrarSenha = signal(false);

  entrar() {
    // TODO: trocar por autenticação real quando existir
    this.router.navigate(['app/pacientes']);
  }
}
