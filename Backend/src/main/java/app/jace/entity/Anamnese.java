package app.jace.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Anamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prontuario; //sera fk para prontuario manyToOne

    private LocalDate data;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(columnDefinition = "TEXT")
    private String medicamentosEmUso;

    @Column(columnDefinition = "TEXT")
    private String doencasPreexistentes;

    @Column(columnDefinition = "TEXT")
    private String cirurgiasAnteriores;

    @Column(columnDefinition = "TEXT")
    private String historicoFamiliar;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
