import { Component, inject, signal } from '@angular/core';
import { Procedimento } from '../../models/procedimento';
import { ProcedimentoService } from '../../services/procedimento-service';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule],
  selector: 'app-procedimento-list',
  styleUrl: './procedimento-list.scss',
  templateUrl: './procedimento-list.html',
})
export class ProcedimentoList {
  procedimentos = signal<Procedimento[]>([]);
  proceServ = inject(ProcedimentoService);



  constructor(){
    this.findAllProce();

  }

  findAllProce(){
    this.proceServ.buscaProcedimentos().subscribe({
      next: retornoProce =>{
        this.procedimentos.set(retornoProce);
      },
      error: erro => {
        console.log(erro);
      }
    })

  }
}
