import { Component, inject, signal } from '@angular/core';
import { Profissional } from '../../models/profissional';
import { ProfissionalService } from '../../services/profissional-service';

@Component({
  imports: [],
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
}
