import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Paciente } from '../models/paciente';

@Service()
export class PacienteService {

    // estabelecimento dos parametros de conexão com o backend
    httpClient = inject(HttpClient);
    API = "http://localhost:8081/api/paciente/";

    // Serviços para paciente

    // busca todos
    buscaPacientes(): Observable<Paciente[]>{
        return this.httpClient.get<Paciente[]>(this.API+"buscatodos");
    }

}
