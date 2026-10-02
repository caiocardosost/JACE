package app.jace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Evolucao;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {

}
