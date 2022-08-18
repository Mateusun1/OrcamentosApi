package br.com.api.orcamentosApi.Controller.Dto;

import br.com.api.orcamentosApi.Modelo.Receita;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;

@Getter
@Setter
public class ReceitasDto {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public ReceitasDto(Receita receita){
        this.id = receita.getR_id();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = receita.getData();
    }
}
