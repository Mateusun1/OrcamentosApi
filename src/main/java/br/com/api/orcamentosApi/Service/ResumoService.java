package br.com.api.orcamentosApi.Service;

import br.com.api.orcamentosApi.Controller.Dto.ResumoDto;
import br.com.api.orcamentosApi.Modelo.Despesa;
import br.com.api.orcamentosApi.Modelo.Receita;
import br.com.api.orcamentosApi.Repository.DespesasRepository;
import br.com.api.orcamentosApi.Repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class ResumoService {

    @Autowired
    private DespesasRepository despesasRepository;


    Despesa despesa = new Despesa();


    Receita receita = new Receita();


    @Autowired
    private ReceitaRepository receitaRepository;


    public ResumoDto resumir(int ano, int mes) {

        LocalDate data = LocalDate.of(ano, mes, 1);

        Date convertFirstDay = Date.valueOf(data.with(TemporalAdjusters.firstDayOfMonth()));
        Date convertLastDay = Date.valueOf(data.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDate firstDay = convertFirstDay.toLocalDate();
        LocalDate lastDay = convertLastDay.toLocalDate();


        Optional<BigDecimal> despesaTotal = despesasRepository.somarResultado(firstDay, lastDay);
        Optional<BigDecimal> receitaTotal = receitaRepository.somarResultado(firstDay, lastDay);



        List<?> valor = despesasRepository.findAllByCategoria(firstDay,lastDay);

        return new ResumoDto(receitaTotal.orElse(BigDecimal.ZERO), despesaTotal.orElse(BigDecimal.ZERO), valor);
    }
}
