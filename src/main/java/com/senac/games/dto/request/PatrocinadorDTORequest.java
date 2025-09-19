package com.senac.games.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class PatrocinadorDTORequest {
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, max=255, message= "O nome tem de ter entre 3 e 255 caracteres")
    private String nome;
    private String representanteNome;
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

  public String getRepresentanteNome() {
    return representanteNome;
  }

  public void setRepresentanteNome(String representanteNome) {
    this.representanteNome = representanteNome;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
