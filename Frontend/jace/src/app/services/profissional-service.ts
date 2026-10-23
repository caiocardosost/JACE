import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Profissional } from '../models/profissional';
import { Observable } from 'rxjs';

@Service()
export class ProfissionalService {

    // estabelecimento dos parametros de conexão com o backend
    httpClient = inject(HttpClient);
    API = "http://localhost:8081/api/profissional/";

    // Serviços para profissional

    // busca todos
    buscaProfissionais(): Observable<Profissional[]>{
        return this.httpClient.get<Profissional[]>(this.API+"buscatodos");
    }

    // busca por id
    buscaProfissionalId(index: number): Observable<Profissional>{
        return this.httpClient.get<Profissional>(this.API+"busca/"+index);
    }

    // editar profissional
    editarProfissional(index: number, profissional: Profissional): Observable<string>{
        return this.httpClient.put<string>(this.API+"editar/"+index, profissional, {responseType: 'text' as 'json'});
    }

    // remover por id
    removeProfissionalId(index: number): Observable<string>{
        return this.httpClient.delete<string>(this.API+"remover/"+index, {responseType: 'text' as 'json'});
    }

    // Registra profissional
    registaProfissional(profissional: Profissional): Observable<string>{
        return this.httpClient.post<string>(this.API+"novo", profissional,{responseType: 'text' as 'json'});
    }
}
