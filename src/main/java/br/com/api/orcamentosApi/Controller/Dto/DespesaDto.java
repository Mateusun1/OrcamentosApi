package br.com.api.orcamentosApi.Controller.Dto;

import br.com.api.orcamentosApi.Enum.Categoria;
import br.com.api.orcamentosApi.Modelo.Despesa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class DespesaDto {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data = LocalDate.now();
    private Categoria categoria;




    public DespesaDto(){}
    public DespesaDto (Despesa despesa){
        this.id = despesa.getD_id();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.data = despesa.getData();
        this.categoria = despesa.getCategoria();
    }


}
