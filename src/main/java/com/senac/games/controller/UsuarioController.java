package com.senac.games.controller;

import com.senac.games.entities.dto.request.security.UsuarioDTOLoginRequest;
import com.senac.games.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping("/login")
  public ResponseEntity<UsuarioDTOLoginResponse> login(@RequestBody UsuarioDTOLoginRequest dtoLoginRequest) {
    return ResponseEntity.ok(usuarioService.login(dtoLoginRequest))
  }

  @PostMapping("/criar")
  public ResponseEntity<UsuarioDTO> criar(
      @RequestBody UsuarioDTOLoginRequest dtoLoginRequest){
   return ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTORequest dtoRequest)
  }
}
