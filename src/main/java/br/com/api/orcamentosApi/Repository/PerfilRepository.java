package br.com.api.orcamentosApi.Repository;

import br.com.api.orcamentosApi.Modelo.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Perfil findByNome(String role_usuario);
}
