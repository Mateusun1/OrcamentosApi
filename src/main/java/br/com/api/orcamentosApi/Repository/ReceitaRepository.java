package br.com.api.orcamentosApi.Repository;

import br.com.api.orcamentosApi.Modelo.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita,Long> {
    List<Receita> findByDescricao(String descricao);

    @Query("select r from Receita r where YEAR(r.data) = ?1 and MONTH(r.data) = ?2")
    List<Receita> findByDataYearMonth(int ano, int mes);
    @Query("select sum(valor) as valorReceita from Receita r where r.data between ?1 and ?2")
    Optional<BigDecimal> somarResultado(LocalDate firstDay, LocalDate lastDay);
}
