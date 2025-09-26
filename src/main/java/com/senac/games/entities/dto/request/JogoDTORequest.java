package com.senac.games.entities.dto.request;

import jakarta.validation.constraints.*;

public class JogoDTORequest {
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, max=255, message= "O nome tem de ter entre 3 e 255 caracteres")
    private String nome;

  @NotNull
  @NotEmpty
    private Integer categoriaId;
    @NotEmpty
    @Min(1)
    @Max(2)
  private Integer status;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
