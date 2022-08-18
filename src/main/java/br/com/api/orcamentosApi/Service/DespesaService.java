package br.com.api.orcamentosApi.Service;

import br.com.api.orcamentosApi.Controller.Dto.DespesaDto;
import br.com.api.orcamentosApi.Controller.Form.DespesaForm;
import br.com.api.orcamentosApi.Modelo.Despesa;
import br.com.api.orcamentosApi.Repository.DespesasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesasRepository despesasRepository;

    @Autowired
    private ModelMapper modelMapper;
    public DespesaDto salvar(DespesaForm form) {
        Despesa despesa = form.converter();
        despesasRepository.save(despesa);
        return new DespesaDto(despesa);
    }

    public List<DespesaDto> listarTudo() {
        List<Despesa> despesasList = despesasRepository.findAll();
        return despesasList.stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public DespesaDto listarId(Long id) {
        Despesa despesaId = despesasRepository.getById(id);
        return new DespesaDto(despesaId);
    }

    public DespesaDto alterar(Long id, DespesaForm form) {
        Despesa despesaId = despesasRepository.getById(id);
        despesaId.setDescricao(form.getDescricao());
        despesaId.setValor(form.getValor());
        return new DespesaDto(despesaId);
    }

    public void deletar(Long id) {
        despesasRepository.deleteById(id);
    }

    public List<DespesaDto> listarPorDescricao(String descricao) {
        List<Despesa> despesas = despesasRepository.findAllByDescricao(descricao);
        return despesas.stream().map(DespesaDto::new).collect(Collectors.toList());
    }


    public List<DespesaDto> listarMes(int ano, int mes) {
        List<Despesa> byMes = despesasRepository.findByDataYearMonth(ano, mes);
        return byMes.stream().map(DespesaDto::new).collect(Collectors.toList());

    }

    public List<DespesaDto> listarDia(int dia) {
        List<Despesa> byDataDay = despesasRepository.findByDataDay(dia);
        return byDataDay.stream().map(DespesaDto::new).collect(Collectors.toList());
    }
}
