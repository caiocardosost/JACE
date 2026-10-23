import { Component, inject, signal } from '@angular/core';
import { Procedimento } from '../../models/procedimento';
import { ProcedimentoService } from '../../services/procedimento-service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  imports: [CommonModule, RouterLink],
  selector: 'app-procedimento-list',
  styleUrl: './procedimento-list.scss',
  templateUrl: './procedimento-list.html',
})
export class ProcedimentoList {
  procedimentos = signal<Procedimento[]>([]);
  proceServ = inject(ProcedimentoService);



  constructor(){
    this.findAll();

  }

  findAll(){
    this.proceServ.buscaProcedimentos().subscribe({
      next: retornoProce =>{
        this.procedimentos.set(retornoProce);
      },
      error: erro => {
        console.log(erro);
      }
    })

  }

  removerProcedimento(index: number){
    this.proceServ.removeProcedimentoId(index).subscribe({
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
