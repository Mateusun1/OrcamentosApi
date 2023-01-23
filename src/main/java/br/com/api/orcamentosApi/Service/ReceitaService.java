package br.com.api.orcamentosApi.Service;

import br.com.api.orcamentosApi.Controller.Dto.ReceitasDto;
import br.com.api.orcamentosApi.Controller.Form.ReceitaForm;
import br.com.api.orcamentosApi.Modelo.Receita;
import br.com.api.orcamentosApi.Repository.ReceitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ModelMapper modelMap;


    public ReceitasDto cadastrar(ReceitaForm form) {
        Receita receita = modelMap.map(form, Receita.class);
        receitaRepository.save(receita);
        return new ReceitasDto(receita);
    }

    public List<ReceitasDto> listarTudo() {
        List<Receita> receitas = receitaRepository.findAll();
        return receitas.stream().map(ReceitasDto::new).collect(Collectors.toList());
    }

    public ReceitasDto listarPorId(Long id) {
        Receita receitaId = receitaRepository.getById(id);
        return new ReceitasDto(receitaId);
    }

    public ReceitasDto alterar(Long id,@RequestBody ReceitaForm form) {
        return form.alterar(id, receitaRepository);
    }

    public String deletar(Long id) {
        receitaRepository.deleteById(id);
        return "Deletado com sucesso!";
    }

    public List<ReceitasDto> listarPorDescricao(String descricao) {
        List<Receita> byDescricao = receitaRepository.findByDescricao(descricao);
        return byDescricao.stream().map(ReceitasDto::new).collect(Collectors.toList());
    }

    public List<ReceitasDto> listarMes(int ano, int mes) {
        List<Receita> byDataYearMonth = receitaRepository.findByDataYearMonth(ano, mes);
        return byDataYearMonth.stream().map(ReceitasDto::new).collect(Collectors.toList());
    }

}
