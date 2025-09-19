package com.senac.games.dto.request.jogo;

import jakarta.validation.constraints.*;

public class JogoUpdateCategoriaDTORequest {
  @NotNull
  @NotEmpty
  private Integer categoriaId;

  public Integer getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
  }
}
