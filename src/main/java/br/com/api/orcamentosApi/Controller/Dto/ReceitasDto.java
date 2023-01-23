package br.com.api.orcamentosApi.Controller.Dto;

import br.com.api.orcamentosApi.Modelo.Receita;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;

@Getter
@Setter
public class ReceitasDto {
    private String descricao;
    private BigDecimal valor;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    public ReceitasDto(){}

    public ReceitasDto(Receita receita){
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = receita.getData();
    }
}
