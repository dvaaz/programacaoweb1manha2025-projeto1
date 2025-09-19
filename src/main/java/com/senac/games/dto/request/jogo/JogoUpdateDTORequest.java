package com.senac.games.dto.request.jogo;

import jakarta.validation.constraints.*;

public class JogoUpdateDTORequest {
  @NotBlank(message = "O campo não pode estar vazio")
  @Size(min=3, max=255, message= "O nome tem de ter entre 3 e 255 caracteres")
  private String nome;


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
