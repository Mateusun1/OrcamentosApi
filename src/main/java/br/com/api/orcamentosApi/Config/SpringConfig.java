package br.com.api.orcamentosApi.Config;

import br.com.api.orcamentosApi.Config.Autenticacao.AutenticacaoViaTokenFilter;
import br.com.api.orcamentosApi.Repository.UsuarioRepository;
import br.com.api.orcamentosApi.Service.TokenService;
import br.com.api.orcamentosApi.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( HttpMethod.POST,"/usuarios/cadastrar").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers( "/perfil/**").hasRole("ADMIN")
                .antMatchers("/despesas/**").hasRole("USUARIO")
                .antMatchers("/receitas").hasRole("USUARIO")
                .anyRequest().authenticated()
                .and().csrf().disable().authorizeRequests()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
