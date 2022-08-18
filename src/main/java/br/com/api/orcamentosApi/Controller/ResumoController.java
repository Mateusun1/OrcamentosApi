package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.ResumoDto;
import br.com.api.orcamentosApi.Service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResumoDto resumir(@PathVariable("ano") int ano, @PathVariable("mes") int mes){
        return resumoService.resumir(ano,mes);
    }
}
