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

    // busca por id
    buscaPacienteId(index: number): Observable<Paciente>{
        return this.httpClient.get<Paciente>(this.API+"busca/"+index);
    }

    // remover por id
    removePacienteId(index: number): Observable<string>{
        return this.httpClient.delete<string>(this.API+"remover/"+index, {responseType: 'text' as 'json'});
    }

    // editar paciente id
    editarPaciente(index: number, paciente: Paciente): Observable<string>{
        return this.httpClient.put<string>(this.API+"editar/"+index, paciente, {responseType: 'text' as 'json'});
    }

    // Registra paciente
    registaPacientes(paciente: Paciente): Observable<string>{
        return this.httpClient.post<string>(this.API+"novo", paciente,{responseType: 'text' as 'json'});
    }


}
