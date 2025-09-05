package com.senac.games.dto.request;

import jakarta.validation.constraints.*;

public class PremioDTORequest {
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, max=500, message= "O nome tem de ter entre 3 e 500 caracteres")
    private String descricao;
    @NotNull
    private Integer ordem;
    @NotEmpty
    @Min(1)
    @Max(2)
    private Integer status;

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Integer getOrdem() {
    return ordem;
  }

  public void setOrdem(Integer ordem) {
    this.ordem = ordem;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
