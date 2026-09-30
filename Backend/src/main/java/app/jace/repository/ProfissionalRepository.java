package app.jace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
