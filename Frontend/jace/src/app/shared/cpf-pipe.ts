import { Pipe, PipeTransform } from '@angular/core';

/**
 * Formata um CPF guardado só com dígitos.
 *   "12345678909" -> "123.456.789-09"
 * Se não tiver 11 dígitos, devolve o valor como veio.
 *
 * Uso: {{ paciente.cpf | cpf }}
 */
@Pipe({ name: 'cpf' })
export class CpfPipe implements PipeTransform {
  transform(valor: string | null | undefined): string {
    const digitos = (valor ?? '').replace(/\D/g, '');
    if (digitos.length !== 11) return valor ?? '';
    return `${digitos.slice(0, 3)}.${digitos.slice(3, 6)}.${digitos.slice(6, 9)}-${digitos.slice(9)}`;
  }
}