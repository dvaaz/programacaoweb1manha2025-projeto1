package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.request.StatusUpdateDTORequest;
import com.senac.games.dto.response.StatusUpdateDTOResponse;
import com.senac.games.entities.Participante;
import com.senac.games.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

  //busca por todos objetos atraves do metodo findAll do Spring
    public List<Participante> listarParticipantes() {
        return this.participanteRepository.findAll();
    }

    public List<Participante> listarParticipantesAtivos() { return this.participanteRepository.listarParticipantes(); }

  //busca por todos objetos atraves do metodo findById do Spring
    public Participante listarPorParticipanteId(Integer participanteId) {
      return this.participanteRepository.findById(participanteId).orElse(null);
    }

    public Participante obterParticipantePorID(Integer participanteId) {
      Participante participante = this.participanteRepository.listarParticipantePorID(participanteId);
      if (participante != null) {
        return this.participanteRepository.listarParticipantePorID(participanteId);
      } else return null;
    }


  @Transactional
    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest dtoRequest) {
        // Mapper recebe o DTO de um tipo e o transforma em Participante
        Participante participante = modelMapper.map(dtoRequest, Participante.class);


       participante.setNome(dtoRequest.getNome());
        participante.setEmail(dtoRequest.getEmail());
        participante.setEndereco(dtoRequest.getEndereco());
        participante.setStatus(dtoRequest.getStatus());
        participante.setIdentificacao(dtoRequest.getIdentificacao());


        //Salva o Participante no repository utilizando o método .save
        Participante participanteSave = this.participanteRepository.save(participante);


        ParticipanteDTOResponse dtoResponse = new ParticipanteDTOResponse();
        dtoResponse.setId(participanteSave.getId());
        dtoResponse.setNome(participanteSave.getNome());
        dtoResponse.setEmail(participanteSave.getEmail());
        dtoResponse.setEndereco(participanteSave.getEndereco());
        dtoResponse.setIdentificacao(participanteSave.getIdentificacao());
        dtoResponse.setStatus(participanteSave.getStatus());



        return dtoResponse;
    }

  @Transactional
  public ParticipanteDTOResponse atualizarParticipante(Integer participanteId, ParticipanteDTORequest dtoRequest) {
    // antes de atualizar verifica se o registro existe
    Participante participante = this.listarPorParticipanteId(participanteId);

    // se encontrar o registro a ser atualizado
    if (participante != null) {
      modelMapper.map(dtoRequest, participante);
      Participante tempResponse = participanteRepository.save(participante);
      return modelMapper.map(tempResponse, ParticipanteDTOResponse.class);

    } else {
      return null;
    }
  }

  @Transactional
  public StatusUpdateDTOResponse atualizarStatusParticipante(Integer participanteId, StatusUpdateDTORequest dtoUpdateRequest) {
    //antes de atualizar busca se existe o registro a ser atualizar
    Participante participante = this.listarPorParticipanteId(participanteId);

    //se encontra o registro a ser atualizado
    if (participante != null) {
      //atualizamos unicamente o campo de status
      participante.setStatus(dtoUpdateRequest.getStatus());

      //com o objeto no formato correto tipo "participante" o comando "save" salva
      // no banco de dados o objeto atualizado
      Participante tempResponse = participanteRepository.save(participante);
      return modelMapper.map(tempResponse, StatusUpdateDTOResponse.class);
    }
    else{
      return null;
    }
  }

  @Transactional
  public void apagarParticipante(Integer participanteId) {
    this.participanteRepository.apagarLogicoParticipante(participanteId);

  }
}
