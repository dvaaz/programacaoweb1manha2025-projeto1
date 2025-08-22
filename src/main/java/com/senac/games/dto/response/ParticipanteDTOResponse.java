package com.senac.games.dto.response;

public class ParticipanteDTOResponse {
  private int id;
  private String nome;
  private String email;
  private String indentificacao;
  private String endereco;
  private int status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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
