package br.com.api.orcamentosApi.Modelo;

import br.com.api.orcamentosApi.Enum.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long d_id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Categoria categoria = Categoria.OUTRAS;

    public Despesa(String descricao, BigDecimal valor, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Despesa( BigDecimal valor, Categoria categoria) {
        this.valor = valor;
        this.categoria = categoria;
    }
}
