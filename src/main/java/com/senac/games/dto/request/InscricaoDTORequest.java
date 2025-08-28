package com.senac.games.dto.request;

import java.util.Date;

public class InscricaoDTORequest {
  private Date data;
  private int status;

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
