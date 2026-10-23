import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Procedimento } from '../../models/procedimento';
import { ProcedimentoService } from '../../services/procedimento-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  imports: [FormsModule],
  selector: 'app-procedimento-details',
  styleUrl: './procedimento-details.scss',
  templateUrl: './procedimento-details.html',
})
export class ProcedimentoDetails {
  procedimento = signal<Procedimento>(new Procedimento());
  proceServ = inject(ProcedimentoService);
  router = inject(ActivatedRoute);
  router2 = inject(Router);
  modoEdicao = signal(false);
  carregando = signal(false);
  salvando = signal(false);
  erroServidor = signal('');

  constructor(){
    let index = this.router.snapshot.params['id'];
    if (index > 0 ){
      this.modoEdicao.set(true);
      this.buscaProcedimento(index);
    }     
  }

  buscaProcedimento(index: number){
    this.carregando.set(true);
    this.proceServ.buscaProcedimentoId(index).subscribe({
      next: procedimento =>{
        this.procedimento.set(procedimento);
        this.carregando.set(false);
      },
      error: erro => {
        console.error(erro);
      }
    })
  }

  salvar(){
    this.salvando.set(true);
    this.erroServidor.set('');
    if(this.modoEdicao()){
      this.editaProce();
    }
    else {
      this.registraProce()
    }
  }

  registraProce(){
    this.proceServ.registaProcedimento(this.procedimento()).subscribe({
      next: resposta => {
        alert("Procedimento cadastrado com sucesso!");
        this.router2.navigate(["app/procedimentos"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar");
      }
    })
  }

  editaProce(){
    this.proceServ.editarProcedimento(this.procedimento().id, this.procedimento()).subscribe({
      next: resposta => {
        alert("Procedimento editado com sucesso!");
        this.router2.navigate(["app/procedimentos"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar");
      }
    })
  }

  cancelar(){
    this.router2.navigate(["app/procedimentos"]);
  }
}
