package quixada.npi.springproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quixada.npi.springproject.exception.StandardError;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.service.CursoService;
import quixada.npi.springproject.service.UsuarioService;
@RestController
@RequestMapping("/curso")
public class CursoController {

	@Autowired
    private CursoService cursoService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        // Busca usuário pelo id e retornar usuário...
    	try {
    		Curso curso= cursoService.findById(id).get();
    		return ResponseEntity.ok(curso);
    	}
    	catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).
    				body(new StandardError(404, "not found", "The ID provited is not valited", "/usuarios/"+id));
    	}
    	  
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Curso curso) {
    	// check if the user exists
    	// as long as the users doesnot exist so then add the users 
    	try {
    		return ResponseEntity.ok(cursoService.addCurso(curso));
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).
    				body(new StandardError(HttpStatus.IM_USED.value(), "already in use", e.getMessage(), "/usuarios"));
    	}
    	
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
    		try {
    			cursoService.deleteCurso(id);
    			return ResponseEntity.ok().build();
    		}
    		catch(Exception e) {
    			return badrequestCreator(e.getMessage(),"The id provided do not exists","delete");
    		}
    }

    private ResponseEntity<?> badrequestCreator(final String cause, final String message, final String action){
    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new StandardError(HttpStatus.BAD_REQUEST.value(), cause, message, "/usuarios/"+action));
    }
    
     @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Curso curso) {
    	 	try {
    	 		cursoService.updateCurso(curso);
    	 		return ResponseEntity.ok().build();
    	 	}
    	 	catch(Exception e) {
    	 		return badrequestCreator(e.getMessage(), "Could not update the user", "update");
    	 	}
    }
}

