package br.com.api.orcamentosApi.Controller;

import br.com.api.orcamentosApi.Modelo.Perfil;
import br.com.api.orcamentosApi.Repository.PerfilRepository;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {


    private PerfilRepository perfilRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid Perfil perfil){
        return ResponseEntity.ok(perfilRepository.save(perfil));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Perfil>> listar(){
        return ResponseEntity.ok(perfilRepository.findAll());
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar (@PathVariable Long id, Perfil perfil){
        Perfil byId = perfilRepository.getById(id);
        byId.setNome(perfil.getNome());
        return ResponseEntity.ok(byId);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
        perfilRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
