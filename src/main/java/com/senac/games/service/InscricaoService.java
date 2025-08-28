package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.entities.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
  private final InscricaoRepository inscricaoRepository;

  @Autowired
  private ModelMapper modelMapper;
  public InscricaoService(InscricaoRepository inscricaoRepository) {
    this.inscricaoRepository = inscricaoRepository;

  }

  public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
    Inscricao inscricao = modelMapper.map(inscricaoDTORequest, Inscricao.class);
    Inscricao inscricaoSave = this.inscricaoRepository.save(inscricao);
    InscricaoDTOResponse inscricaoDTOResponse = modelMapper.map(inscricaoSave, InscricaoDTOResponse.class);
    return inscricaoDTOResponse;
  }

  public List<Inscricao> listarInscricoes() {
    return this.inscricaoRepository.findAll();

  }
}
