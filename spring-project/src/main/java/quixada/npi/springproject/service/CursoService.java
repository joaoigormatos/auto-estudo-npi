package quixada.npi.springproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quixada.npi.springproject.exception.StandardError;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	private CursoRepository cursoRepository;
    

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

	public Optional<Curso> findById(int id) {
		return cursoRepository.findById(id);
	}

	public Curso addCurso(Curso curso) throws Exception{
		if(cursoRepository.findById(curso.getId())!=null)
			throw new Exception("Course already exists");
		cursoRepository.save(curso);
		return curso;
	}
	public void deleteCurso(int id) throws Exception{
		if(cursoRepository.existsById(id)) {
			cursoRepository.deleteById(id);
			return;
		}
		throw new Exception("The id passed is not valid");
	}

	public void updateCurso(Curso curso) throws Exception {
		if(cursoRepository.existsById(curso.getId())) {
			cursoRepository.delete(curso);
			cursoRepository.save(curso);
		}
		throw new Exception("Course does not exists");
	}
}
