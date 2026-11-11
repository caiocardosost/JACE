import { Pipe, PipeTransform } from '@angular/core';

/**
 * Transforma o nome de um enum do back-end em texto para a tela.
 *   "ATIVO"         -> "Ativo"
 *   "EM_TRATAMENTO" -> "Em tratamento"
 *   "MASCULINO"     -> "Masculino"
 *
 * Uso: {{ paciente.status | rotulo }}
 */
@Pipe({ name: 'rotulo' })
export class RotuloPipe implements PipeTransform {
  transform(valor: string | null | undefined): string {
    if (!valor) return '';
    const texto = valor.replace(/_/g, ' ').toLowerCase();
    return texto.charAt(0).toUpperCase() + texto.slice(1);
  }
}
