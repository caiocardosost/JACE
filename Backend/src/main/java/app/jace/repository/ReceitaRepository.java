package app.jace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
