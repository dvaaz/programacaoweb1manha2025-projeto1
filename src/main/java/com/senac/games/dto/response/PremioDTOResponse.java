package com.senac.games.dto.response;

public class PremioDTOResponse {
  private Integer id;
  private String descricao;
  private int ordem;
  private int status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getOrdem() {
    return ordem;
  }

  public void setOrdem(int ordem) {
    this.ordem = ordem;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
