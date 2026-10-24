import { Component, inject, signal } from '@angular/core';
import { PacienteService } from '../../services/paciente-service';
import { Paciente } from '../../models/paciente';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { IniciaisPipe } from '../../shared/iniciais-pipe';

@Component({
  imports: [RouterLink, IniciaisPipe],
  selector: 'app-paciente-list',
  styleUrl: './paciente-list.scss',
  templateUrl: './paciente-list.html',
})
export class PacienteList {
  pacientes = signal<Paciente[]>([]);
  pacServ = inject(PacienteService);
  router = inject(ActivatedRoute);

  constructor(){
    this.findAll();
  }

  findAll(){
    this.pacServ.buscaPacientes().subscribe({
      next: listaPac =>{
        console.log("Resposta da API: ", listaPac);
        this.pacientes.set(listaPac);
      },
      error: erro => {
        console.error(erro);
      }
    })

  }

  removerPaciente(index: number){
    if (!confirm('Remover este paciente? Essa ação não pode ser desfeita.')) return;

    this.pacServ.removePacienteId(index).subscribe({
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
