package com.senac.games.dto.response.inscricao;

import jakarta.validation.constraints.*;

import java.util.Date;

public class InscricaoUpdateDTOResponse {
  private Integer id;
  @NotBlank(message = "O campo não pode ser deixado em branco")
  private Date data;
  @NotNull
  @NotEmpty
  private int participanteId;
  @NotNull
  @NotEmpty
  private int jogoId;
  @NotNull
  @NotEmpty
  @Min(1)
  @Max(2)
  private Integer status;

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

  public int getParticipanteId() {
    return participanteId;
  }

  public void setParticipanteId(int participanteId) {
    this.participanteId = participanteId;
  }

  public int getJogoId() {
    return jogoId;
  }

  public void setJogoId(int jogoId) {
    this.jogoId = jogoId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
