package app.jace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
