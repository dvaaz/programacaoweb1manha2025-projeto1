package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.entities.Jogo;
import com.senac.games.repository.JogoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

  private final JogoRepository jogoRepository;
  private final ModelMapper modelMapper;

  public JogoService(JogoRepository jogoRepository, ModelMapper modelMapper) {
    this.jogoRepository = jogoRepository;
    this.modelMapper = modelMapper;
  }

  public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
    Jogo jogo = modelMapper.map(jogoDTORequest, Jogo.class);
    Jogo jogoSave = this.jogoRepository.save(jogo);
    JogoDTOResponse jogoDTOResponse = modelMapper.map(jogoSave, JogoDTOResponse.class);

    return jogoDTOResponse;
  }

  public List<Jogo> listarJogos() {
    return this.jogoRepository.findAll();

  }

  public Jogo lisarJogoPorId(int jogoId) {
    return this.jogoRepository.findById(jogoId).orElse(null);
  }

}
