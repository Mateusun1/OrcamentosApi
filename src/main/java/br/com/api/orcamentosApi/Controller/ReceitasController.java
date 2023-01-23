package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.ReceitasDto;
import br.com.api.orcamentosApi.Controller.Form.ReceitaForm;
import br.com.api.orcamentosApi.Service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/cadastrar")
    public ReceitasDto cadastrar(@RequestBody ReceitaForm form){
        return receitaService.cadastrar(form);
    }

    @GetMapping("/listar")
    public List<ReceitasDto> listar(String descricao){
        if (descricao == null){
        return receitaService.listarTudo();
        }else{
            return receitaService.listarPorDescricao(descricao);
        }
    }

    @GetMapping("/listar/{ano}/{mes}")
    public List<ReceitasDto> listarMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes){
        return receitaService.listarMes(ano, mes);
    }

    @GetMapping("/listar/{id}")
    public ReceitasDto listarPorId(@PathVariable Long id){
        return receitaService.listarPorId(id);
    }

    @PutMapping("/alterar/{id}")
    @Transactional
    public ReceitasDto alterar(@PathVariable Long id, @RequestBody ReceitaForm form){
        return receitaService.alterar(id, form);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        receitaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
