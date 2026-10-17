import { Component, inject, signal } from '@angular/core';
import { Paciente } from '../../models/paciente';
import { ActivatedRoute, RouterOutlet } from '@angular/router';

@Component({
  imports: [],
  selector: 'app-paciente-view',
  styleUrl: './paciente-view.scss',
  templateUrl: './paciente-view.html',
})
export class PacienteView {

  paciente = signal<Paciente>(new Paciente);

  router = inject(ActivatedRoute)

  constructor(){//TODO implementar
  }
}
