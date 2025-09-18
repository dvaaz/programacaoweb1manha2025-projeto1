package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.request.JogoDTOUpdateStatusRequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateStatusResponse;
import com.senac.games.entities.Categoria;
import com.senac.games.entities.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class JogoService {
    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public JogoService(JogoRepository jogoRepository, CategoriaRepository categoriaRepository) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
      Categoria categoria = this.categoriaRepository.obterCategoriaPorID(jogoDTORequest.getCategoriaId());

      if (categoria != null) {
        Jogo jogo = new Jogo();
        jogo.setCategoria(categoria);
        jogo.setNome(jogoDTORequest.getNome());
        jogo.setStatus(jogoDTORequest.getStatus());

        Jogo jogoSave = this.jogoRepository.save(jogo);

        JogoDTOResponse dtoResponse = new JogoDTOResponse();
        dtoResponse.setId(jogo.getId());
        dtoResponse.setIdCategoria(jogo.getCategoria().getId());
        dtoResponse.setNome(jogo.getNome());
        dtoResponse.setStatus(jogo.getStatus());
        return dtoResponse;
      }
        return null;
    }

    public List<JogoDTOResponse> listarJogos() {
      List<Jogo> jogos = this.jogoRepository.listarJogos();
      List<JogoDTOResponse> listaJogosResponse = new ArrayList<>();
      for (Jogo jogo : jogos) {
        JogoDTOResponse dtoResponse = new JogoDTOResponse();
        dtoResponse.setId(jogo.getId());
        dtoResponse.setIdCategoria(jogo.getCategoria().getId());
        dtoResponse.setNome(jogo.getNome());
        dtoResponse.setStatus(jogo.getStatus());
        listaJogosResponse.add(dtoResponse);

      } return listaJogosResponse;

    }

  public Jogo buscarJogoPorId(Integer jogoId) {
    Jogo jogo = this.jogoRepository.obterJogoPorID(jogoId);
    return jogo;
  }

    public JogoDTOResponse listarJogoPorId(Integer jogoId) {
        Jogo jogo = buscarJogoPorId(jogoId);
        if (jogo != null) {
            JogoDTOResponse dtoResponse = new JogoDTOResponse();
            dtoResponse.setId(jogo.getId());
            dtoResponse.setIdCategoria(jogo.getCategoria().getId());
            dtoResponse.setNome(jogo.getNome());
            dtoResponse.setStatus(jogo.getStatus());
            return dtoResponse;
        } else return null;
    }

    public JogoDTOUpdateStatusResponse atualizarStatusJogo(Integer jogoId, JogoDTOUpdateStatusRequest jogoDTOUpdateStatusRequest) {
        Jogo jogo = this.buscarJogoPorId(jogoId);
        if (jogo != null) {
            jogo.setStatus(jogoDTOUpdateStatusRequest.getStatus());

            Jogo tempResponse = jogoRepository.save(jogo);
            return modelMapper.map(tempResponse, JogoDTOUpdateStatusResponse.class);
        } else return null;
    }

    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {

        Jogo jogo = this.buscarJogoPorId(jogoId);
        if (jogo != null) {
            jogo.setNome(jogoDTORequest.getNome());
            jogo.setStatus(jogoDTORequest.getStatus());
            jogo.setCategoria(
                categoriaRepository.findById(jogoDTORequest.getCategoriaId()).orElseThrow(()->new IllegalArgumentException("Categoria não encontrada com id: "+jogoDTORequest.getCategoriaId()))
                );
            Jogo jogoSave = jogoRepository.save(jogo);
            return modelMapper.map(jogoSave, JogoDTOResponse.class);
        } else{
            // Error 400 caso tente atualiza jogo inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarJogo(Integer jogoId){
        this.jogoRepository.apagarLogicoJogo(jogoId);
    }
}
