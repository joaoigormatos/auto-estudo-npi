package quixada.npi.springproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.repository.UsuarioRepository;
import quixada.npi.springproject.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

	@Override
	public Optional<Usuario> findById(int id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario addUser(Usuario usuario) throws Exception{
		if(usuarioRepository.findByEmail(usuario.getEmail())!=null)
			throw new Exception("User already exists");
		usuarioRepository.save(usuario);
		return usuario;
	}
	@Override
	public void deleteUser(int id) throws Exception{
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return;
		}
		throw new Exception("The id passed is not valid");
	}

	@Override
	public void updateUser(Usuario usuario) throws Exception {
		if(usuarioRepository.existsById(usuario.getId())) {
			usuarioRepository.delete(usuario);
			usuarioRepository.save(usuario);
		}
		throw new Exception("User does not exists");
	}

}
