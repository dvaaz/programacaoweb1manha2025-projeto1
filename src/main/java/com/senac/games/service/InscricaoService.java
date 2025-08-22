package com.senac.games.service;

import com.senac.games.entities.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
  private final InscricaoRepository inscricaoRepository;

  public InscricaoService(InscricaoRepository inscricaoRepository) {
    this.inscricaoRepository = inscricaoRepository;

  }

  public List<Inscricao> listarInscricoes() {
    return this.inscricaoRepository.findAll();

  }
}
