import { Pipe, PipeTransform } from '@angular/core';

/**
 * Transforma um nome nas suas iniciais.
 *   "Maria Aparecida Silva" -> "MS"  (primeiro + último nome)
 *   "Pedro"                 -> "P"
 *   "" / null / undefined   -> "?"
 *
 * Uso no HTML: {{ paciente().nome | iniciais }}
 */

@Pipe({ name: 'iniciais' })
export class IniciaisPipe implements PipeTransform {
  transform(nome: string | null | undefined): string {
    const partes = (nome ?? '').trim().split(/\s+/).filter(Boolean);
    if (partes.length === 0) return '?';

    const primeira = partes[0][0];
    const ultima = partes.length > 1 ? partes[partes.length - 1][0] : '';
    return (primeira + ultima).toUpperCase();
  }
}