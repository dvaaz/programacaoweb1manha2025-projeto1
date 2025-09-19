package com.senac.games.dto.request.inscricao;

public class InscricaoUpdateParticipanteDTORequest {
  private Integer inscricaoId;
  private Integer participanteId;

  public Integer getInscricaoId() {
    return inscricaoId; }

  public void setInscricaoId(Integer inscricaoId) {
    this.inscricaoId = inscricaoId;
  }

  public Integer getParticipanteId() {
    return participanteId;
  }

  public void setParticipanteId(Integer participanteId) {
    this.participanteId = participanteId;
  }
}
