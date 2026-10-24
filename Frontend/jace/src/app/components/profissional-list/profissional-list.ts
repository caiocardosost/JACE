import { Component, inject, signal } from '@angular/core';
import { Profissional } from '../../models/profissional';
import { ProfissionalService } from '../../services/profissional-service';
import { RouterLink } from '@angular/router';
import { IniciaisPipe } from '../../shared/iniciais-pipe';

@Component({
  imports: [RouterLink, IniciaisPipe],
  selector: 'app-profissional-list',
  styleUrl: './profissional-list.scss',
  templateUrl: './profissional-list.html',
})
export class ProfissionalList {
  profissionais = signal<Profissional[]>([]);
  proServ = inject(ProfissionalService);

  constructor(){
    this.findAll();
  }

  findAll(){
    this.proServ.buscaProfissionais().subscribe({
      next: listaPro =>{
        console.log("Resposta da API: ", listaPro);
        this.profissionais.set(listaPro);
      },
      error: erro => {
        console.error(erro);
      }
    })

  }

  removerProfissional(index: number){
    if (!confirm('Remover este profissional? Essa ação não pode ser desfeita.')) return;

    this.proServ.removeProfissionalId(index).subscribe({
      next: resultado =>{
        alert("Removido com sucesso!");
        this.findAll();
      },
      error: erro => {
        console.error(erro);
      }
    })

  }
}
