package br.com.api.orcamentosApi.Controller.Form;

import br.com.api.orcamentosApi.Enum.Categoria;
import br.com.api.orcamentosApi.Modelo.Despesa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DespesaForm {
    private String descricao;
    private BigDecimal valor;
    private Categoria categoria = Categoria.OUTRAS;

    public Despesa converter() {
        return new Despesa(descricao,valor,categoria);
    }
}
