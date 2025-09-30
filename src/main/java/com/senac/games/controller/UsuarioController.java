package com.senac.games.controller;

import com.senac.games.entities.dto.request.security.UsuarioDTOLoginRequest;
import com.senac.games.entities.dto.request.usuario.UsuarioDTORequest;
import com.senac.games.entities.dto.response.security.UsuarioDTOLoginResponse;
import com.senac.games.entities.dto.response.usuario.UsuarioDTOResponse;
import com.senac.games.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping("/login")
  public ResponseEntity<UsuarioDTOLoginResponse> login(@RequestBody UsuarioDTOLoginRequest dtoLoginRequest) {
    return ResponseEntity.ok(usuarioService.login(dtoLoginRequest));
  }

  @PostMapping("/criar")
  public ResponseEntity<UsuarioDTOResponse> criar(
      @RequestBody UsuarioDTORequest dtoRequest){
   return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criar(dtoRequest));
  }
}
