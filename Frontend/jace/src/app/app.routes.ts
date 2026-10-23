import { Routes } from '@angular/router';
import { LoginComponent } from './components/layout/login-component/login-component';
import { PrincipalComponent } from './components/layout/principal-component/principal-component';
import { PacienteList } from './components/paciente-list/paciente-list';
import { PacienteDetails } from './components/paciente-details/paciente-details';
import { ProfissionalList } from './components/profissional-list/profissional-list';
import { ProfissionalDetails } from './components/profissional-details/profissional-details';
import { ProcedimentoList } from './components/procedimento-list/procedimento-list';
import { ProcedimentoDetails } from './components/procedimento-details/procedimento-details';
import { PacienteView } from './components/paciente-view/paciente-view';

export const routes: Routes = [
    {path:"", redirectTo: "login", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path:"app", component: PrincipalComponent,
        children:[
            {path:"pacientes", component: PacienteList},
            {path:"paciente/novo", component: PacienteDetails},
            {path: "paciente/editar/:id", component: PacienteDetails},
            {path:"paciente/:id", component: PacienteView},            
            {path:"profissionais", component: ProfissionalList},
            {path:"profissional/novo", component: ProfissionalDetails},
            {path: "profissional/editar/:id", component: ProfissionalDetails},
            {path:"procedimentos", component: ProcedimentoList},
            {path:"procedimento/novo", component: ProcedimentoDetails},
            {path: "procedimento/editar/:id", component: ProcedimentoDetails}
        ]
    }
];
