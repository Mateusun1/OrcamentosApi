package br.com.api.orcamentosApi.Controller.Form;

import br.com.api.orcamentosApi.Modelo.Perfil;
import br.com.api.orcamentosApi.Modelo.Usuario;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
public class UsuarioAtualizar {
    @NotBlank
    private String nome;
    @NotBlank
    private String senha;


}
