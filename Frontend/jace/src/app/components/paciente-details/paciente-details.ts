import { Component, inject, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Paciente } from '../../models/paciente';
import { PacienteService } from '../../services/paciente-service';

// Deixa só os números ("123.456.789-09" -> "12345678909")
const soDigitos = (valor?: string | null) => (valor ?? '').replace(/\D/g, '');

@Component({
  imports: [FormsModule],
  selector: 'app-paciente-details',
  styleUrl: './paciente-details.scss',
  templateUrl: './paciente-details.html',
})
export class PacienteDetails {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private pacServ = inject(PacienteService);

  // Tudo que muda depois de uma chamada à API (ou que o HTML exibe) é signal
  paciente = signal<Paciente>(new Paciente());
  modoEdicao = signal(false);
  carregando = signal(false);
  salvando = signal(false);
  erroServidor = signal('');

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id > 0) {
      this.modoEdicao.set(true);
      this.buscaPaciente(id);
    }
  }

  private buscaPaciente(id: number) {
    this.carregando.set(true);
    this.pacServ.buscaPacienteId(id).subscribe({
      next: (p) => {
        this.paciente.set(p);
        this.carregando.set(false);
      },
      error: (erro) => {
        console.error(erro);
        this.carregando.set(false);
        this.erroServidor.set('Não foi possível carregar o paciente.');
      },
    });
  }

  salvar() {
    this.salvando.set(true);
    this.erroServidor.set('');

    this.pacServ.registaPacientes(this.dadosParaEnvio()).subscribe({
      next: () => {
        alert('Paciente registrado com sucesso!');
        this.router.navigate(['app/pacientes']);
      },
      error: (erro: HttpErrorResponse) => {
        console.error(erro);
        this.salvando.set(false); // libera o botão para tentar de novo
        this.erroServidor.set(this.mensagemDeErro(erro));
        // NÃO redireciona: o usuário continua na tela com tudo que digitou
      },
    });
  }

  // Menor de 18 anos? (a API exige os dados do responsável legal nesse caso)
  menorDeIdade(): boolean {
    const nascimento = this.paciente().dataNascimento; // 'yyyy-MM-dd'
    if (!nascimento) return false;
    const hoje = new Date();
    const hojeNumero = hoje.getFullYear() * 10000 + (hoje.getMonth() + 1) * 100 + hoje.getDate();
    const idade = Math.floor((hojeNumero - Number(nascimento.replace(/-/g, ''))) / 10000);
    return idade < 18;
  }

  // Mostra a seção do responsável para menores, ou se já houver dados preenchidos
  exibeResponsavel(): boolean {
    const p = this.paciente();
    return this.menorDeIdade() || !!(p.responsavelNome || p.responsavelCpf || p.responsavelTelefone);
  }

  // Monta o que vai para a API: CPFs só com dígitos e campos vazios omitidos
  private dadosParaEnvio(): Paciente {
    const p = this.paciente();
    const dados: Partial<Paciente> = {
      ...p,
      cpf: soDigitos(p.cpf),
      sexo: p.sexo || undefined,
      responsavelNome: p.responsavelNome?.trim() || undefined,
      responsavelCpf: soDigitos(p.responsavelCpf) || undefined,
      responsavelTelefone: p.responsavelTelefone?.trim() || undefined,
      idade: undefined, // a idade é calculada pela API
    };
    return dados as Paciente;
  }

  cancelar() {
    this.router.navigate(['app/pacientes']);
  }

  private mensagemDeErro(erro: HttpErrorResponse): string {
    if (erro.status === 0) {
      return 'Não foi possível conectar ao servidor. Tente novamente em instantes.';
    }
    if (erro.status === 409) {
      return 'Já existe um paciente cadastrado com este CPF.';
    }
    // Regra de negócio da API (400): o corpo da resposta é o próprio texto da mensagem
    if (typeof erro.error === 'string' && erro.error) {
      return erro.error;
    }
    // Usa a mensagem da API quando ela existir; senão, um texto genérico
    return erro.error?.message ?? 'Não foi possível salvar o paciente. Confira os dados e tente novamente.';
  }
}
