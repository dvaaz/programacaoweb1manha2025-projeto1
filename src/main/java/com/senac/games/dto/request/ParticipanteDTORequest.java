package com.senac.games.dto.request;

import jakarta.validation.constraints.*;

public class ParticipanteDTORequest {
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=3, max=255, message= "O nome tem de ter entre 3 e 255 caracteres")
    private String nome;
    @Email(message="Insira um email valido")
    private String email;
    @NotBlank(message = "Preencha uma identificacao")
    private String indentificacao;
    @NotBlank(message = "O campo não pode estar vazio")
    @Size(min=5, max=255, message= "O endereco tem de ter entre 5 e 255 caracteres")
    private String endereco;
    @NotEmpty
    @Min(1)
    @Max(2)
    private int status;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIndentificacao() {
    return indentificacao;
  }

  public void setIndentificacao(String indentificacao) {
    this.indentificacao = indentificacao;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
