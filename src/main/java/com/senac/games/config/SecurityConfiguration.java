package com.senac.games.config;

import com.senac.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableAutoConfiguration
public class SecurityConfiguration {
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private UserAuthenticationFilter userAuthenticationFilter;

  public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
      "/usuario/login", // Url que usaremos para fazer login
      "/usuario", // Url que usaremos para criar um usuário
//      "/h2-console", // guarda em memória as informações  enão no banco de dados
      "/v3/api-docs/**",
      "/swagger-ui.html",
  };

  //Endpoints fora de SecurityFilterChain para facilitar a leitura
  // Endpoints que requerem autenticação para serem acessados
  public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
      "/api/**",
      "usuario/listar",

  };

  // Endpoints que só podem ser acessador por usuários com permissão de cliente
  public static final String [] ENDPOINTS_CUSTOMER = {
      "/api/jogo"
  };

  // Endpoints que só podem ser acessador por usuários com permissão de administrador
  public static final String [] ENDPOINTS_ADMIN = {
      "/api/categoria"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
            .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
            .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
            .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
            .anyRequest().denyAll()
        )
        .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
