package br.com.api.orcamentosApi.Controller.Form;

import br.com.api.orcamentosApi.Modelo.Usuario;
import br.com.api.orcamentosApi.Repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioForm {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public Usuario converter() {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String senhaCriptografada = bCrypt.encode(senha);
        return new Usuario(nome,email,senhaCriptografada);
    }


    public boolean isRepeatVerify(UsuarioRepository usuarioRepository) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}
