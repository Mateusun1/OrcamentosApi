package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.DespesaDto;
import br.com.api.orcamentosApi.Controller.Form.DespesaForm;
import br.com.api.orcamentosApi.Enum.Categoria;
import br.com.api.orcamentosApi.Service.DespesaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DespesaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private DespesaDto despesaDto;
    @Mock
    private DespesaForm despesaForm;
    @MockBean
    private DespesaService despesaService;

    @BeforeEach
    void init(){
        despesaDto = new DespesaDto();
        despesaDto.setDescricao("Geladeira");
        despesaDto.setCategoria(Categoria.MORADIA);
        despesaDto.setValor(new BigDecimal("1500"));

        despesaForm = new DespesaForm();
        despesaForm.setDescricao("Geladeira");
        despesaForm.setCategoria(Categoria.MORADIA);
        despesaForm.setValor(new BigDecimal("1500"));
    }

    @Test
    void deveCadastrarUmaDespesa() throws Exception {
        when(despesaService.salvar(despesaForm)).thenReturn(despesaDto);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDto);

        mockMvc.perform(post("/despesas/cadastrar").content(despesaDtoAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    void naoDeveCadastrarUmaDespesa() throws Exception {
        DespesaForm despesaNula = null;
        when(despesaService.salvar(despesaNula)).thenReturn(null);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaNula);

        mockMvc.perform(post("/despesas/cadastrar").content(despesaDtoAsJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveListarDespesas() throws Exception {
        List<DespesaDto> despesaDtoList = new ArrayList<>();
        despesaDtoList.add(despesaDto);

        when(despesaService.listarTudo()).thenReturn(despesaDtoList);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDtoList);
        mockMvc.perform(get("/despesas/listar")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(content().json(despesaDtoAsJson));
    }

    @Test
    void deveListarDespesasPorDescricao() throws Exception {
        List<DespesaDto> despesaDtoList = new ArrayList<>();
        despesaDtoList.add(despesaDto);

        when(despesaService.listarPorDescricao("Geladeira")).thenReturn(despesaDtoList);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDtoList);
        mockMvc.perform(get("/despesas/listar?descricao=" + despesaDto.getDescricao())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(content().json(despesaDtoAsJson));
    }

    @Test
    void deveListarDespesaPorMes() throws Exception {
        List<DespesaDto> despesaDtoList = new ArrayList<>();
        despesaDtoList.add(despesaDto);

        when(despesaService.listarMes(2022,11)).thenReturn(despesaDtoList);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDtoList);
        mockMvc.perform(get("/despesas/listar/" + 2022 + "/" + 11)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(content().json(despesaDtoAsJson));
    }

    @Test
    void deveListarDespesaPeloDia() throws Exception {
        List<DespesaDto> despesaDtoList = new ArrayList<>();
        despesaDtoList.add(despesaDto);

        when(despesaService.listarDia(11)).thenReturn(despesaDtoList);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDtoList);
        mockMvc.perform(get("/despesas/listarDia/" + 11)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(content().json(despesaDtoAsJson));
    }

    @Test
    void deveListarDespesaPorId() throws Exception {
        when(despesaService.listarId(1L)).thenReturn(despesaDto);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDto);
        mockMvc.perform(get("/despesas/listar/" + 1)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andExpect(content().json(despesaDtoAsJson));
    }

    @Test
    void deveAlterarDespesa() throws Exception {
        despesaForm.setDescricao("Cadeira");
        despesaDto.setDescricao("Cadeira");

        when(despesaService.alterar(1L, despesaForm)).thenReturn(despesaDto);

        ObjectMapper mapper  = new ObjectMapper();
        String despesaDtoAsJson = mapper.writeValueAsString(despesaDto);
        mockMvc.perform(put("/despesas/alterar/1")
                        .content(despesaDtoAsJson)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarDespesaPorId() throws Exception {
        when(despesaService.deletar(1L)).thenReturn("Deletado com Sucesso!");

        mockMvc.perform(delete("/despesas/deletar/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}