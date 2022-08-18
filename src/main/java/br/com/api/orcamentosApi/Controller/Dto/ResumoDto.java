package br.com.api.orcamentosApi.Controller.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ResumoDto {
    private BigDecimal valorReceita;
    private BigDecimal valorDespesa;
    private BigDecimal saldoFinal;
    private List<?> categorias;


    public ResumoDto(BigDecimal receita, BigDecimal despesa, List<?> despesaDto) {
        this.valorReceita = receita;
        this.valorDespesa = despesa;
        this.saldoFinal = receita.subtract(despesa);
        this.categorias = despesaDto;
    }
}
