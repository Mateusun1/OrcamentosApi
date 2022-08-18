package br.com.api.orcamentosApi.Controller.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoriaForm {
    @NotBlank
    private String nome;
}
