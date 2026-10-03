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
}
