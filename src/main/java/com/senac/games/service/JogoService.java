package com.senac.games.service;

import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.request.JogoDTOUpdateStatusRequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateStatusResponse;
import com.senac.games.entities.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Jogo jogo = modelMapper.map(jogoDTORequest, Jogo.class);

        Jogo jogoSave = this.jogoRepository.save(jogo);
        JogoDTOResponse jogoDTOResponse = modelMapper.map(jogoSave, JogoDTOResponse.class);

        return jogoDTOResponse;
    }

    public List<Jogo> listarJogos() { return this.jogoRepository.listarJogos(); }

    public Jogo listarJogoPorId(Integer jogoId) {
        Jogo jogo = this.jogoRepository.obterJogoPorID(jogoId);
        if (jogo != null) {
            return this.jogoRepository.obterJogoPorID(jogoId);
        } else return null;
    }

    public JogoDTOUpdateStatusResponse atualizarStatusJogo(Integer jogoId, JogoDTOUpdateStatusRequest jogoDTOUpdateStatusRequest) {
        Jogo jogo = this.listarJogoPorId(jogoId);
        if (jogo != null) {
            jogo.setStatus(jogoDTOUpdateStatusRequest.getStatus());

            Jogo tempResponse = jogoRepository.save(jogo);
            return modelMapper.map(tempResponse, JogoDTOUpdateStatusResponse.class);
        } else return null;
    }

    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {

        Jogo jogo = this.listarJogoPorId(jogoId);
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
