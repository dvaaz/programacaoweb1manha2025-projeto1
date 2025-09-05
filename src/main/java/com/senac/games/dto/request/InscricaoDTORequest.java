package com.senac.games.dto.request;

import jakarta.validation.constraints.*;

import java.util.Date;

public class InscricaoDTORequest {
    @NotBlank(message = "O campo não pode ser deixado em branco")
    private Date data;
    @NotNull
    @NotEmpty
    @Min(1)
    @Max(2)
  private Integer status;

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
