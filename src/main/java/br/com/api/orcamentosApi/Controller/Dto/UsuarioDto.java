package br.com.api.orcamentosApi.Controller.Dto;

import br.com.api.orcamentosApi.Modelo.Perfil;
import br.com.api.orcamentosApi.Modelo.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDto {
    private String nome;
    private String email;
    private List<Perfil> perfis;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfis = usuario.getPerfis();
    }
}
