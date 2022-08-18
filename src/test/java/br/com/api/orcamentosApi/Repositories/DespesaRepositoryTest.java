package br.com.api.orcamentosApi.Repositories;

import br.com.api.orcamentosApi.Modelo.Despesa;
import br.com.api.orcamentosApi.Repository.DespesasRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DespesaRepositoryTest {
    
    @Autowired
    private DespesasRepository repository;

    @Test
    public void buscarDespesaPorDescricao(){
        String descricao = "armario";
        Despesa byDescricao = repository.findByDescricao(descricao);
        Assert.assertNotNull(byDescricao);
        Assert.assertEquals(descricao,byDescricao.getDescricao());
    }

    @Test
    public void buscarDescricaoNãoExistente(){
        String descricao = "jogo";
        Despesa byDescricao = repository.findByDescricao(descricao);
        Assert.assertNull(byDescricao);
    }
}
