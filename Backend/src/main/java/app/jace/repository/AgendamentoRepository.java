package app.jace.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.jace.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	
	public List<Agendamento> findByData(LocalDate data);

}
