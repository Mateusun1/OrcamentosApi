package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.ReceitasDto;
import br.com.api.orcamentosApi.Controller.Form.DespesaForm;
import br.com.api.orcamentosApi.Controller.Form.ReceitaForm;
import br.com.api.orcamentosApi.Service.ReceitaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ReceitasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReceitasDto receitasDto;

    @Mock
    private ReceitaForm receitaForm;

    @MockBean
    private ReceitaService receitaService;

    @BeforeEach
    void init(){
        receitasDto = new ReceitasDto();
        receitasDto.setDescricao("Comida");
        receitasDto.setValor(new BigDecimal("110"));
        receitasDto.setData(LocalDate.of(2022,11,21));

        receitaForm = new ReceitaForm();
        receitaForm.setDescricao("Comida");
        receitaForm.setValor(new BigDecimal("110"));
    }

    @Test
    void deveCadastrarReceita() throws Exception {
        when(receitaService.cadastrar(receitaForm)).thenReturn(receitasDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String receitaAsJson = mapper.writeValueAsString(receitasDto);

        System.out.println(receitaAsJson);

        mockMvc.perform(post("/receitas/cadastrar")
                .content(receitaAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    void naoDeveCadastrarReceita() throws Exception {
        receitaForm.setValor(null);
        when(receitaService.cadastrar(receitaForm)).thenReturn(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String receitaAsJson = mapper.writeValueAsString(null);

        mockMvc.perform(post("/receitas/cadastrar")
                        .content(receitaAsJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveListarlistarTudo() throws Exception {
        List<ReceitasDto> receitaList = new ArrayList<>();
        receitaList.add(receitasDto);

        when(receitaService.listarTudo()).thenReturn(receitaList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        mockMvc.perform(get("/receitas/listar")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }
    @Test
    void deveListarlistarPorDescricao() throws Exception {
        List<ReceitasDto> receitaList = new ArrayList<>();
        receitaList.add(receitasDto);

        when(receitaService.listarPorDescricao("comida")).thenReturn(receitaList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        mockMvc.perform(get("/receitas/listar?descricao=" + receitasDto.getDescricao())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void deveListarReceitasPorMes() throws Exception {
        List<ReceitasDto> receitaList = new ArrayList<>();
        receitaList.add(receitasDto);

        when(receitaService.listarMes(2022,11)).thenReturn(receitaList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        mockMvc.perform(get("/receitas/listar/" + 2022 + "/" + 11)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarReceitaPorId() throws Exception {
        when(receitaService.listarPorId(1L)).thenReturn(receitasDto);

        mockMvc.perform(get("/receitas/listar/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveAlterarReceita() throws Exception {
        receitaForm.setDescricao("Lanche");
        receitasDto.setDescricao("Lanche");

        when(receitaService.alterar(1L,receitaForm)).thenReturn(receitasDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String receitaDtoAsJson = mapper.writeValueAsString(receitasDto);

        mockMvc.perform(put("/receitas/alterar/1")
                        .content(receitaDtoAsJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deletar() throws Exception {
        when(receitaService.deletar(1L)).thenReturn("Deletado com sucesso!");

        mockMvc.perform(delete("/receitas/deletar/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}