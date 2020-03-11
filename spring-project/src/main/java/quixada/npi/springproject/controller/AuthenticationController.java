package quixada.npi.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quixada.npi.springproject.config.JwtTokenProvider;
import quixada.npi.springproject.dto.UserRegisterDTO;
import quixada.npi.springproject.exception.StandardError;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.repository.UsuarioRepository;
import quixada.npi.springproject.service.UsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Object> signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Usuario usuario = usuarioRepository.findByEmail(username);
            
            List<String> roleList = roles(usuario);
            
            String token = jwtTokenProvider.createToken(username,roleList);
            
           
            
            Map<Object, Object> model = new HashMap<>();
            model.put("username", usuario.getNome());
            model.put("token", "Bearer " + token);
            return ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
    }
    @PostMapping("/singup")
    public ResponseEntity<?> create(@RequestBody UserRegisterDTO userRegisterDTO) {
        // check if the user exists
        // as long as the users doesnot exist so then add the users
        try {
            Usuario usuario = userRegisterDTO.toUser();
            return ResponseEntity.ok(usuarioService.addUser(usuario));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).
                    body(new StandardError(HttpStatus.IM_USED.value(), "already in use", e.getMessage(), "/usuarios"));
        }

    }

   private List<String> roles(UserDetails userDetails){
	   List<String> auxRoles = new ArrayList<>();
	   for(GrantedAuthority grantedAuthority:  userDetails.getAuthorities()) {
		   auxRoles.add(grantedAuthority.getAuthority());
	   }
	   return auxRoles;
   }
}
