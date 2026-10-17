import { Component, inject, signal } from '@angular/core';
import { PacienteService } from '../../services/paciente-service';
import { Paciente } from '../../models/paciente';
import { RouterLink } from '@angular/router';

@Component({
  imports: [RouterLink],
  selector: 'app-paciente-list',
  styleUrl: './paciente-list.scss',
  templateUrl: './paciente-list.html',
})
export class PacienteList {
  pacientes = signal<Paciente[]>([]);
  pacServ = inject(PacienteService);

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
}
