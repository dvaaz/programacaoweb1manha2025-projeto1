package com.senac.games.entities.dto.request.security;

import com.senac.games.enumerator.RoleName;

public record CriarUsuarioDTO(
    String login,
    String senha,
    RoleName role
) {
}
