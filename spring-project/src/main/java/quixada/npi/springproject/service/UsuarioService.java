package quixada.npi.springproject.service;


import quixada.npi.springproject.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario findByEmail(String email);

    List<Usuario> findAll();
    
    Optional<Usuario> findById(int id);
    
    Usuario addUser(Usuario usuario) throws Exception;
    
    public void deleteUser(int id) throws Exception;

	void updateUser(Usuario usuario) throws Exception;
}
