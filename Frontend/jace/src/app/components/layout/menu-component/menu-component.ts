import { Component, HostListener, inject, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { IniciaisPipe } from '../../../shared/iniciais-pipe';

interface ItemMenu {
  rotulo: string;
  rota: string;
  disponivel: boolean; // false = aparece como "Em breve" e não clica
}

@Component({
  imports: [RouterLink, RouterLinkActive, IniciaisPipe],
  selector: 'app-menu-component',
  styleUrl: './menu-component.scss',
  templateUrl: './menu-component.html',
})
export class MenuComponent {
  private router = inject(Router);

  // Por enquanto fixos; depois virão do usuário logado (token / API)
  clinica = signal({ nome: 'JACE' });
  usuario = signal({ nome: 'Usuário', perfil: 'Administrador' });

  // Quando criar a rota de uma tela, é só trocar "disponivel" para true
  itens: ItemMenu[] = [
    { rotulo: 'Pacientes',     rota: '/app/pacientes',     disponivel: true },
    { rotulo: 'Agenda',        rota: '/app/agenda',        disponivel: false },
    { rotulo: 'Profissionais', rota: '/app/profissionais', disponivel: true },
    { rotulo: 'Procedimentos', rota: '/app/procedimentos', disponivel: true },
    { rotulo: 'Financeiro',    rota: '/app/financeiro',    disponivel: false },
    { rotulo: 'Estoque',       rota: '/app/estoque',       disponivel: false },
  ];

  menuAberto = signal(false);        // menu ☰ no mobile
  menuUsuarioAberto = signal(false); // dropdown do usuário

  sair() {
    this.menuUsuarioAberto.set(false);
    // TODO: limpar a sessão/token quando existir autenticação
    this.router.navigate(['/']); // ajuste para a rota do seu login
  }

  // Fecha o dropdown ao clicar fora dele
  @HostListener('document:click', ['$event'])
  aoClicarNoDocumento(evento: Event) {
    const alvo = evento.target as HTMLElement;
    if (!alvo.closest('.menu__usuario')) {
      this.menuUsuarioAberto.set(false);
    }
  }

  @HostListener('document:keydown.escape')
  aoApertarEsc() {
    this.menuUsuarioAberto.set(false);
    this.menuAberto.set(false);
  }
}