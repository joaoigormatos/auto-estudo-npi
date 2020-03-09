package quixada.npi.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quixada.npi.springproject.model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	Curso findByNome(String nome);
	
	Curso findBySigla(String sigla);

}
