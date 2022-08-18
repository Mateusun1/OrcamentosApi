package br.com.api.orcamentosApi.Controller.Dto;

import br.com.api.orcamentosApi.Enum.Categoria;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoriaDto {
    private Categoria nome;
    private BigDecimal valor;

    public CategoriaDto(Categoria categoria, BigDecimal valor){
        this.nome = categoria;
        this.valor = valor;
    }
}
