package com.senac.games.service;



import com.senac.games.config.SecurityConfiguration;
import com.senac.games.entities.Role;
import com.senac.games.entities.Usuario;
import com.senac.games.entities.dto.request.security.UsuarioDTOLoginRequest;
import com.senac.games.entities.dto.request.usuario.UsuarioDTORequest;
import com.senac.games.entities.dto.response.security.UsuarioDTOLoginResponse;
import com.senac.games.entities.dto.response.usuario.UsuarioDTOResponse;
import com.senac.games.enumerator.RoleName;
import com.senac.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenService jwtTokenService;
  @Autowired
  private SecurityConfiguration securityConfiguration;


  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public UsuarioDTOResponse criar(UsuarioDTORequest dtoRequest) {


    // Cria um novo usuário com os dados fornecidos
    Usuario novoUsuario = new Usuario();
    novoUsuario.setNome(dtoRequest.getNome());
    novoUsuario.setCpf(dtoRequest.getCpf());
    novoUsuario.setDataNascimento(dtoRequest.getDataNascimento());
    novoUsuario.setSenha(dtoRequest.getCpf());
    novoUsuario.setLogin(dtoRequest.getLogin());
    novoUsuario.setStatus(1);

    // Codifica a senha do usuário com o algoritmo bcrypt
    novoUsuario.setSenha(securityConfiguration.passwordEncoder().encode(dtoRequest.getSenha()));

    // Atribui ao usuário um conjunto permissões específicas
    List<Role> roles = new ArrayList<>();
    List<String> dtoRoleList = dtoRequest.getRoleList();
    for (String role : dtoRoleList) {
      Role novaRole = new Role();
      novaRole.setName(RoleName.valueOf(role));
      roles.add(novaRole);
    }


    // Salva o novo usuário no banco de dados
    usuarioRepository.save(novoUsuario);

    UsuarioDTOResponse dtoResponse = new UsuarioDTOResponse();
    dtoResponse.setId(novoUsuario.getId());
    dtoResponse.setNome(novoUsuario.getNome());
    dtoResponse.setCpf(novoUsuario.getCpf());
    dtoResponse.setDataNascimento(novoUsuario.getDataNascimento());
    dtoResponse.setLogin(novoUsuario.getLogin());
    dtoResponse.setSenha(novoUsuario.getSenha());

    return dtoResponse;
  }


  public UsuarioDTOLoginResponse login(UsuarioDTOLoginRequest dtoRequest){
    // Cria um objeto de autenticação com o email e a senha do usuário
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(dtoRequest.getLogin(), dtoRequest.getSenha());

    // Autentica o usuário com as credenciais fornecidas
    Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    // Obtém o objeto UserDetails do usuário autenticado
    UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

    // Gera um token JWT para o usuário autenticado
    UsuarioDTOLoginResponse dtoLoginResponse = new UsuarioDTOLoginResponse();
    dtoLoginResponse.setId(userDetails.getIdUsuario());
    dtoLoginResponse.setNome(userDetails.getNomeUsuario());
    dtoLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
    return dtoLoginResponse;

  }
}
