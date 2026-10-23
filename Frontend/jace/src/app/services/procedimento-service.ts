import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Procedimento } from '../models/procedimento';

@Service()
export class ProcedimentoService {
    // estabelecimento dos parametros de conexão com o backend
    httpClient = inject(HttpClient);
    API = "http://localhost:8081/api/procedimento/";

    // Serviços para profissional

    // busca todos
    buscaProcedimentos(): Observable<Procedimento[]>{
        return this.httpClient.get<Procedimento[]>(this.API+"buscatodos");
    }

    // busca por id
    buscaProcedimentoId(index: number): Observable<Procedimento>{
        return this.httpClient.get<Procedimento>(this.API+"busca/"+index);
    }

    // editar procedimento id
    editarProcedimento(index: number, procedimento: Procedimento): Observable<string>{
        return this.httpClient.put<string>(this.API+"editar/"+index, procedimento, {responseType: 'text' as 'json'});
    }

    // remover por id
    removeProcedimentoId(index: number): Observable<string>{
        return this.httpClient.delete<string>(this.API+"remover/"+index, {responseType: 'text' as 'json'});
    }

    // Registra procedimento
    registaProcedimento(procedimento: Procedimento): Observable<string>{
        return this.httpClient.post<string>(this.API+"novo", procedimento, {responseType: 'text' as 'json'});
    }
}
