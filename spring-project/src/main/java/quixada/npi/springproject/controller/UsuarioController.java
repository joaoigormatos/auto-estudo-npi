package quixada.npi.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import quixada.npi.springproject.dto.UserRegisterDTO;
import quixada.npi.springproject.exception.StandardError;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("")
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		// Busca usuário pelo id e retornar usuário...
		try {
			Usuario usuario = usuarioService.findById(id).get();
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
					.body(new StandardError(404, "not found", "The ID provited is not valited", "/usuarios/" + id));
		}

	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody UserRegisterDTO userDto) {
		// check if the user exists
		// as long as the users doesnot exist so then add the users
		try {
			Usuario usuario = userDto.toUser();
			return ResponseEntity.ok(usuarioService.addUser(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
					.body(new StandardError(HttpStatus.IM_USED.value(), "already in use", e.getMessage(), "/usuarios"));
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		try {
			usuarioService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return badrequestCreator(e.getMessage(), "The id provided do not exists", "delete");
		}
	}

	private ResponseEntity<?> badrequestCreator(final String cause, final String message, final String action) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
				.body(new StandardError(HttpStatus.BAD_REQUEST.value(), cause, message, "/usuarios/" + action));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario) {
		try {
			usuarioService.updateUser(usuario);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return badrequestCreator(e.getMessage(), "Could not update the user", "update");
		}
	}
}
