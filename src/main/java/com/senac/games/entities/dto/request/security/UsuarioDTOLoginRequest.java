package com.senac.games.entities.dto.request.security;

public record UsuarioDTOLoginRequest(
  String login,
  String senha
){}
