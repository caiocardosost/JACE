package app.jace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
