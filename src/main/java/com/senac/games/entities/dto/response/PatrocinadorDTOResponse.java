package com.senac.games.entities.dto.response;

public class PatrocinadorDTOResponse {
  private Integer id;
  private String nome;
  private String representanteNome;
  private int status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
