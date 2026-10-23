import { Component, inject, signal } from '@angular/core';
import { Profissional } from '../../models/profissional';
import { ProfissionalService } from '../../services/profissional-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-profissional-details',
  styleUrl: './profissional-details.scss',
  templateUrl: './profissional-details.html',
})
export class ProfissionalDetails {
  profissional = signal<Profissional>(new Profissional());
  proServ = inject(ProfissionalService);
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
      this.buscaProfissional(index);
    }     
  }

  buscaProfissional(index: number){
    this.carregando.set(true);
    this.proServ.buscaProfissionalId(index).subscribe({
      next: profissional =>{
        this.profissional.set(profissional);
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
      this.editaPro();
    }
    else {
      this.registraPro()
    }
  }

  registraPro(){
    this.proServ.registaProfissional(this.profissional()).subscribe({
      next: resposta => {
        alert("Profissional cadastrado com sucesso!");
        this.router2.navigate(["app/profissionais"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar. Erro no servidor");
      }
    })
  }

  editaPro(){
    this.proServ.editarProfissional(this.profissional().id, this.profissional()).subscribe({
      next: resposta => {
        alert("Profissional editado com sucesso!");
        this.router2.navigate(["app/profissionais"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar. Erro no servidor");
      }
    })
  }

  cancelar(){
    this.router2.navigate(["app/profisisonais"]);
  }
}
