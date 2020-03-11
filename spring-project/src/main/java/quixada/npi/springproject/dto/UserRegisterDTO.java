package quixada.npi.springproject.dto;

import org.springframework.stereotype.Component;
import quixada.npi.springproject.model.Usuario;

@Component
public class UserRegisterDTO {
    private String name;
    private String password;
    private String email;
    private String enabled;


    public Usuario toUser(){
        boolean enabled = this.enabled.equals("Online")?true:false;
        return new Usuario(name,email,password,enabled);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
