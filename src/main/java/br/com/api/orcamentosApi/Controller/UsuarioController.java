package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Controller.Dto.UsuarioDto;
import br.com.api.orcamentosApi.Controller.Form.UsuarioAtualizar;
import br.com.api.orcamentosApi.Controller.Form.UsuarioForm;
import br.com.api.orcamentosApi.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form){

        if (usuarioService.isRepeat(form)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse email já está cadastrado");
        }
        return ResponseEntity.ok(usuarioService.cadastrar(form));
    }

    @GetMapping("/listarTudo")
    public ResponseEntity<List<UsuarioDto>> listarTudo(){
        return ResponseEntity.ok(usuarioService.listarTudo());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioDto> editar(@PathVariable Long id, @RequestBody UsuarioAtualizar form){
        return ResponseEntity.ok(usuarioService.editar(id,form));
    }

}
