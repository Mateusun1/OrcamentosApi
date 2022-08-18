package br.com.api.orcamentosApi.Controller.Form;

import br.com.api.orcamentosApi.Controller.Dto.ReceitasDto;
import br.com.api.orcamentosApi.Modelo.Receita;
import br.com.api.orcamentosApi.Repository.ReceitaRepository;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReceitaForm {
    private String descricao;
    private BigDecimal valor;


    public ReceitasDto alterar(Long id, ReceitaRepository receitaRepository) {
        Receita receitaId = receitaRepository.getById(id);
        receitaId.setDescricao(this.descricao);
        receitaId.setValor(this.valor);
        return new ReceitasDto(receitaId);
    }
}
