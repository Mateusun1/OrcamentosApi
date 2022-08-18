package br.com.api.orcamentosApi.Repository;

import br.com.api.orcamentosApi.Modelo.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public interface DespesasRepository extends JpaRepository<Despesa,Long> {
    List<Despesa> findAllByDescricao(String descricao);
    Despesa findByDescricao(String descricao);

    @Query("select r from Despesa r where YEAR(r.data) = ?1 and MONTH(r.data) = ?2")
    List<Despesa> findByDataYearMonth(int ano, int mes);
    @Query("select r from Despesa r where DAY(r.data) = ?1")
    List<Despesa> findByDataDay(int dia);

    @Query("select sum(valor) as valorDespesa from Despesa o where o.data between ?1 and ?2")
    Optional<BigDecimal> somarResultado( LocalDate firstDay, LocalDate lastDay);

    @Query(value = "select categoria, sum(valor) from Despesa where data between ?1 and ?2 GROUP BY categoria" ,nativeQuery = true)
    List<?> findAllByCategoria(LocalDate firstDay, LocalDate lastDay);
}
