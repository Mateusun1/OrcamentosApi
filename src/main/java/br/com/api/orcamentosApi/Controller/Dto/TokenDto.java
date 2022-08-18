package br.com.api.orcamentosApi.Controller.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDto {
    private String token;
    private String tipo;


    public TokenDto(String token, String tipo){
        this.token = token;
        this.tipo = tipo;
    }
}
