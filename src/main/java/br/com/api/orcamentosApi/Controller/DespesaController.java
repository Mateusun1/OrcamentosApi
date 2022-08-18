package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.DespesaDto;
import br.com.api.orcamentosApi.Controller.Form.DespesaForm;
import br.com.api.orcamentosApi.Service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping("/cadastrar")
    public DespesaDto cadastrar (@RequestBody @Valid DespesaForm form){
        return despesaService.salvar(form);
    }

    @GetMapping("/listar")
    public List<DespesaDto> listarTudo( String descricao){
        if (descricao == null){
            return despesaService.listarTudo();
        }else{
            return despesaService.listarPorDescricao(descricao);
        }
    }
    @GetMapping("/listar/{ano}/{mes}")
    public List<DespesaDto> listarMes(@PathVariable("ano") int ano,@PathVariable("mes") int mes){
        return despesaService.listarMes(ano,mes);
    }
    @GetMapping("/listarDia/{dia}")
    public List<DespesaDto> listarDia(@PathVariable("dia") int dia){
        return despesaService.listarDia(dia);
    }

    @GetMapping("/listar/{id}")
    public DespesaDto listarId(@PathVariable Long id){
        return despesaService.listarId(id);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public DespesaDto alterar(@PathVariable Long id, @RequestBody DespesaForm form){
        return despesaService.alterar(id, form);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        despesaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
