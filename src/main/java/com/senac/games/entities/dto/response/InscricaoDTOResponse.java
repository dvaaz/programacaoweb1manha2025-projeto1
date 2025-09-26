package com.senac.games.entities.dto.response;

import java.util.Date;

public class InscricaoDTOResponse {
  private Integer id;
  private Date data;
  private int status;

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
}
