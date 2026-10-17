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
}
