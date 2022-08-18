package br.com.api.orcamentosApi.Service;

import br.com.api.orcamentosApi.Controller.Dto.UsuarioDto;
import br.com.api.orcamentosApi.Controller.Form.UsuarioAtualizar;
import br.com.api.orcamentosApi.Controller.Form.UsuarioForm;
import br.com.api.orcamentosApi.Modelo.Perfil;
import br.com.api.orcamentosApi.Modelo.Usuario;
import br.com.api.orcamentosApi.Repository.PerfilRepository;
import br.com.api.orcamentosApi.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public UsuarioDto cadastrar(UsuarioForm form) {
        Usuario usuario = form.converter();
        Perfil perfil = perfilRepository.findByNome("ROLE_USUARIO");
        usuario.addRole(perfil);
        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public boolean isRepeat(UsuarioForm form) {
        return form.isRepeatVerify(usuarioRepository);
    }

    public List<UsuarioDto> listarTudo() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    public UsuarioDto editar(Long id, UsuarioAtualizar form) {
        Usuario usuario = usuarioRepository.getById(id);
        usuario.setNome(form.getNome());
        usuario.setSenha(form.getSenha());
        return new UsuarioDto(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(s);
        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("User Not Found");
    }
}
