import { Routes } from '@angular/router';
import { LoginComponent } from './components/layout/login-component/login-component';
import { PrincipalComponent } from './components/layout/principal-component/principal-component';
import { PacienteList } from './components/paciente-list/paciente-list';
import { PacienteDetails } from './components/paciente-details/paciente-details';
import { ProfissionalList } from './components/profissional-list/profissional-list';
import { ProfissionalDetails } from './components/profissional-details/profissional-details';

export const routes: Routes = [
    {path:"", redirectTo: "login", pathMatch: "full"},
    {path: "login", component: LoginComponent},
    {path:"app", component: PrincipalComponent,
        children:[
            {path:"paciente", component: PacienteList},
            {path:"paciente/novo", component: PacienteDetails},
            {path: "paciente/editar/:id", component: PacienteDetails},            
            {path:"profissional", component: ProfissionalList},
            {path:"profissional/novo", component: ProfissionalDetails},
            {path: "profissional/editar/:id", component: PacienteDetails},
        ]
    }
];
