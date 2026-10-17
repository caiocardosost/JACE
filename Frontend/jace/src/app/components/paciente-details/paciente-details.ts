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
      this.buscaPaciente();
    }     
  }

  buscaPaciente(){
    this.carregando.set(true);
    //TODO implementar a busca e atribuição de paciente
  }

  salvar(){
    this.salvando.set(true);
    this.erroServidor.set('');
    this.pacServ.registaPacientes(this.paciente()).subscribe({
      next: resposta => {
        alert("paciente registrado com sucesso!");
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
