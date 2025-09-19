package com.senac.games.dto.response.inscricao;

import java.util.Date;

public class InscricaoDTOResponse {
  private Integer id;
  private Date data;
  private int status;
  private Integer idParticipante;
  private Integer idJogo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Integer getIdParticipante() {
    return idParticipante;
  }

  public void setIdParticipante(Integer idParticipante) {
    this.idParticipante = idParticipante;
  }

  public Integer getIdJogo() {
    return idJogo;
  }

  public void setIdJogo(Integer idJogo) {
    this.idJogo = idJogo;
  }
}
