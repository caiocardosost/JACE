import { Component, computed, inject, signal } from '@angular/core';
import { DatePipe, TitleCasePipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Paciente } from '../../models/paciente';
import { PacienteService } from '../../services/paciente-service';
import { IniciaisPipe } from '../../shared/iniciais-pipe';

@Component({
  imports: [RouterLink, DatePipe, TitleCasePipe, IniciaisPipe],
  selector: 'app-paciente-view',
  styleUrl: './paciente-view.scss',
  templateUrl: './paciente-view.html',
})
export class PacienteView {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private pacServ = inject(PacienteService);

  private id = Number(this.route.snapshot.paramMap.get('id'));

  paciente = signal<Paciente>(new Paciente());
  carregando = signal(true); // começa true: a busca já dispara no constructor
  erro = signal('');

  // Link do WhatsApp só com os números do telefone (+55 do Brasil)
  linkWhatsapp = computed(() => {
    const numeros = (this.paciente().telefone ?? '').replace(/\D/g, '');
    return `https://wa.me/55${numeros}`;
  });

  constructor() {
    this.buscaPaciente();
  }

  buscaPaciente() {
    if (!this.id) {
      this.carregando.set(false);
      this.erro.set('Paciente não encontrado.');
      return;
    }

    this.carregando.set(true);
    this.erro.set('');

    this.pacServ.buscaPacienteId(this.id).subscribe({
      next: (paciente) => {
        this.paciente.set(paciente);
        this.carregando.set(false);
      },
      error: (erro: HttpErrorResponse) => {
        console.error(erro);
        this.carregando.set(false);
        this.erro.set(
          erro.status === 404
            ? 'Paciente não encontrado.'
            : 'Não foi possível carregar os dados do paciente. Tente novamente.'
        );
        // Não redireciona: a tela mostra o erro com os botões "Tentar novamente" e "Voltar"
      },
    });
  }

  voltar() {
    this.router.navigate(['/app/pacientes']);
  }

  imprimir() {
    window.print();
  }
}