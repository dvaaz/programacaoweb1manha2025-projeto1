package com.senac.games.service;

import com.senac.games.dto.request.inscricao.InscricaoDTORequest;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateJogoDTORequest;
import com.senac.games.dto.request.inscricao.InscricaoUpdateParticipanteDTORequest;
import com.senac.games.dto.response.inscricao.InscricaoDTOResponse;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateJogoDTOResponse;
import com.senac.games.dto.response.inscricao.InscricaoUpdateParticipanteDTOResponse;
import com.senac.games.entities.Inscricao;
import com.senac.games.entities.Jogo;
import com.senac.games.entities.Participante;
import com.senac.games.repository.InscricaoRepository;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.ParticipanteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InscricaoService {
  private final InscricaoRepository inscricaoRepository;
  private final ParticipanteRepository participanteRepository;
  private final JogoRepository jogoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InscricaoService(
        InscricaoRepository inscricaoRepository, ParticipanteRepository participanteRepository , JogoRepository jogoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.participanteRepository = participanteRepository;
        this.jogoRepository = jogoRepository;
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest dtoRequest) {
      Participante participante = participanteRepository.listarParticipantePorID(dtoRequest.getParticipanteId());
      if (participante == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      Jogo jogo = jogoRepository.obterJogoPorID(dtoRequest.getJogoId());
      if (jogo == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      Inscricao inscricao = new Inscricao();
      inscricao.setParticipante(participante);
      inscricao.setJogo(jogo);
      inscricao.setData(dtoRequest.getData());
      inscricao.setStatus(dtoRequest.getStatus());
      inscricaoRepository.save(inscricao);

      InscricaoDTOResponse dtoResponse = new InscricaoDTOResponse();
      dtoResponse.setId(inscricao.getId());
      dtoResponse.setStatus(inscricao.getStatus());
      dtoResponse.setData(inscricao.getData());
      dtoResponse.setIdJogo(inscricao.getJogo().getId());
      dtoResponse.setIdParticipante(inscricao.getParticipante().getId());

        return dtoResponse;
    }

    public List<Inscricao> listarInscricaos() { return this.inscricaoRepository.listarInscricaos(); }

    public Inscricao listarInscricaoPorId(Integer inscricaoId) {
        Inscricao inscricao = this.inscricaoRepository.obterInscricaoPorID(inscricaoId);
        if (inscricao != null) {
            return this.inscricaoRepository.obterInscricaoPorID(inscricaoId);
        } else return null;
    }

    public StatusUpdateDTOResponse atualizarStatusInscricao(Integer inscricaoId, StatusUpdateDTORequest statusUpdateDTORequest) {
        Inscricao inscricao = this.listarInscricaoPorId(inscricaoId);
        if (inscricao != null) {
            inscricao.setStatus(statusUpdateDTORequest.getStatus());

            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelMapper.map(tempResponse, StatusUpdateDTOResponse.class);
        } else return null;
    }

    public InscricaoUpdateParticipanteDTOResponse alterarParticipanteInscricao(Integer inscricaoId, InscricaoUpdateParticipanteDTORequest dtoRequest) {
      Participante participante = participanteRepository.listarParticipantePorID(dtoRequest.getParticipanteId());
      if (participante == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      Inscricao inscricao = this.listarInscricaoPorId(inscricaoId);
      if (inscricao != null && participante != null) {
        inscricao.setParticipante(participante);

        Inscricao inscricaoSave = inscricao;
        inscricaoRepository.save(inscricaoSave);

        InscricaoUpdateParticipanteDTOResponse dtoResponse = new InscricaoUpdateParticipanteDTOResponse();
        dtoResponse.setInscricaoId(inscricao.getId());
        dtoResponse.setParticipanteId(participante.getId());
        return dtoResponse;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public InscricaoUpdateJogoDTOResponse alterarJogoInscricao(Integer inscricaoId, InscricaoUpdateJogoDTORequest dtoRequest) {
      Jogo jogo = jogoRepository.obterJogoPorID(dtoRequest.getIdJogo());
      if (jogo == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }
      Inscricao inscricao = this.listarInscricaoPorId(inscricaoId);
      if (inscricao != null && jogo != null) {
        inscricao.setJogo(jogo);

        Inscricao inscricaoSave = inscricao;
        inscricaoRepository.save(inscricaoSave);

        InscricaoUpdateJogoDTOResponse dtoResponse = new InscricaoUpdateJogoDTOResponse();
        dtoResponse.setInscricaoId(inscricao.getId());
        dtoResponse.setJogoId(jogo.getId());
        return dtoResponse;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


    public InscricaoUpdateDTOResponse atualizarInscricao(
        Integer inscricaoId, InscricaoUpdateDTORequest dtoRequest)  {
      Inscricao inscricao = listarInscricaoPorId(inscricaoId);
        if (inscricao != null) {
          Inscricao inscricaoSave = new Inscricao();
          inscricaoSave.setId(inscricaoId);
          inscricaoSave.setStatus(dtoRequest.getStatus());
          inscricaoSave.setData(dtoRequest.getData());

          InscricaoUpdateDTOResponse dtoResponse = new InscricaoUpdateDTOResponse();
          dtoResponse.setId(inscricao.getId());
          dtoResponse.setData(dtoRequest.getData());
          dtoResponse.setStatus(dtoRequest.getStatus());

          return dtoResponse;

        }             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void apagarInscricao(Integer inscricaoId){
        this.inscricaoRepository.apagarLogicoInscricao(inscricaoId);
    }
}
