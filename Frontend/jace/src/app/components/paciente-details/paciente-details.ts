import { Component, inject, signal } from '@angular/core';
import { Paciente } from '../../models/paciente';
import { ActivatedRoute, Router } from '@angular/router';
import { PacienteService } from '../../services/paciente-service';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-paciente-details',
  styleUrl: './paciente-details.scss',
  templateUrl: './paciente-details.html',
})
export class PacienteDetails {
  paciente = signal<Paciente>(new Paciente());
  pacServ = inject(PacienteService);
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
      this.buscaPaciente(index);
    }     
  }

  buscaPaciente(index: number){
    this.carregando.set(true);
    this.pacServ.buscaPacienteId(index).subscribe({
      next: paciente =>{
        this.paciente.set(paciente);
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
      this.editaPac();
    }
    else {
      this.registraPac()
    }
  }

  registraPac(){
    this.pacServ.registaPacientes(this.paciente()).subscribe({
      next: resposta => {
        alert("Paciente cadastrado com sucesso!");
        this.router2.navigate(["app/pacientes"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar. CPF duplicado ou erro no servidor");
      }
    })
  }

  editaPac(){
    this.pacServ.editarPaciente(this.paciente().id, this.paciente()).subscribe({
      next: resposta => {
        alert("Paciente editado com sucesso!");
        this.router2.navigate(["app/pacientes"]);
      },
      error: erro => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set("Erro ao salvar. CPF duplicado ou erro no servidor");
      }
    })
  }

  cancelar(){
    this.router2.navigate(["app/pacientes"]);
  }
}
