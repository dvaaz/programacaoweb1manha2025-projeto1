package com.senac.games.service;

import com.senac.games.dto.request.jogo.JogoDTORequest;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.request.jogo.JogoUpdateCategoriaDTORequest;
import com.senac.games.dto.request.jogo.JogoUpdateDTORequest;
import com.senac.games.dto.response.jogo.JogoDTOResponse;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.dto.response.jogo.JogoUpdateCategoriaDTOResponse;
import com.senac.games.dto.response.jogo.JogoUpdateDTOResponse;
import com.senac.games.entities.Categoria;
import com.senac.games.entities.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
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

    public StatusUpdateDTOResponse atualizarStatusJogo(Integer jogoId, StatusUpdateDTORequest statusUpdateDTORequest) {
        Jogo jogo = this.buscarJogoPorId(jogoId);
        if (jogo != null) {
            jogo.setStatus(statusUpdateDTORequest.getStatus());

            Jogo tempResponse = jogoRepository.save(jogo);
            return modelMapper.map(tempResponse, StatusUpdateDTOResponse.class);
        } else return null;
    }

    public JogoUpdateDTOResponse atualizarNomeJogo(Integer jogoId, JogoUpdateDTORequest dtoRequest) {

        Jogo jogo = this.buscarJogoPorId(jogoId);
        if (jogo != null) {
            jogo.setNome(dtoRequest.getNome());
            Jogo jogoSave = jogoRepository.save(jogo);

            JogoUpdateDTOResponse dtoResponse = new JogoUpdateDTOResponse();
            dtoResponse.setId(jogoSave.getId());
            dtoResponse.setNome(jogo.getNome());


            return dtoResponse;
        } else{
            // Error 400 caso tente atualiza jogo inexistente.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public JogoUpdateCategoriaDTOResponse alterarCategoria(Integer jogoId, JogoUpdateCategoriaDTORequest dtoRequest) {
      Categoria categoria = categoriaRepository.obterCategoriaPorID(dtoRequest.getCategoriaId());
      if (categoria == null) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      Jogo jogo = this.buscarJogoPorId(jogoId);
      if (jogo != null) {
        jogo.setCategoria(categoria);
        jogoRepository.save(jogo);

        JogoUpdateCategoriaDTOResponse dtoResponse = new JogoUpdateCategoriaDTOResponse();
        dtoResponse.setCategoriaId(jogo.getCategoria().getId());
        dtoResponse.setId(jogo.getId());

        return dtoResponse;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void apagarJogo(Integer jogoId){
        this.jogoRepository.apagarLogicoJogo(jogoId);
    }
}
