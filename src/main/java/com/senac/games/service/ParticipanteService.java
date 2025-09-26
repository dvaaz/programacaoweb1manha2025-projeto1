package com.senac.games.service;

import com.senac.games.entities.dto.request.ParticipanteDTORequest;
import com.senac.games.entities.dto.response.ParticipanteDTOResponse;
import com.senac.games.entities.dto.request.ParticipanteDTOUpdateStatusRequest;
import com.senac.games.entities.dto.response.ParticipanteDTOUpdateStatusResponse;
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
      Participante participante = this.participanteRepository.obterParticipantePorID(participanteId);
      if (participante != null) {
        return this.participanteRepository.obterParticipantePorID(participanteId);
      } else return null;
    }


  @Transactional
    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {
        // Mapper recebe o DTO de um tipo e o transforma em Participante
        Participante participante = modelMapper.map(participanteDTORequest, Participante.class);


       /* participante.setNome(participanteDTO.getNome());
        participante.setEmail(participanteDTO.getEmail());
        participante.setEndereco(participanteDTO.getEndereco());
        participante.setStatus(participanteDTO.getStatus());
        participante.setIdentificacao(participanteDTO.getIndentificacao());
        */

        //Salva o Participante no repository utilizando o método .save
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = modelMapper.map(participanteSave, ParticipanteDTOResponse.class);

        /* ParticipanteDTOResponse participanteDTOResponse = new ParticipanteDTOResponse();
        participanteDTOResponse.setId(participanteSave.getId());
        participanteDTOResponse.setNome(participanteSave.getNome());
        participanteDTOResponse.setEmail(participanteSave.getEmail());
        participanteDTOResponse.setEndereco(participanteSave.getEndereco());
        participanteDTOResponse.setIndentificacao(participanteSave.getIdentificacao());
        participanteDTOResponse.setStatus(participanteSave.getStatus());
        */


        return participanteDTOResponse;
    }

  @Transactional
  public ParticipanteDTOResponse atualizarParticipante(Integer participanteId, ParticipanteDTORequest participanteDTORequest) {
    // antes de atualizar verifica se o registro existe
    Participante participante = this.listarPorParticipanteId(participanteId);

    // se encontrar o registro a ser atualizado
    if (participante != null) {
      modelMapper.map(participanteDTORequest, participante);
      Participante tempResponse = participanteRepository.save(participante);
      return modelMapper.map(tempResponse, ParticipanteDTOResponse.class);

    } else {
      return null;
    }
  }

  @Transactional
  public ParticipanteDTOUpdateStatusResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTOUpdateStatusRequest participanteDTOUpdateRequest) {
    //antes de atualizar busca se existe o registro a ser atualizar
    Participante participante = this.listarPorParticipanteId(participanteId);

    //se encontra o registro a ser atualizado
    if (participante != null) {
      //atualizamos unicamente o campo de status
      participante.setStatus(participanteDTOUpdateRequest.getStatus());

      //com o objeto no formato correto tipo "participante" o comando "save" salva
      // no banco de dados o objeto atualizado
      Participante tempResponse = participanteRepository.save(participante);
      return modelMapper.map(tempResponse, ParticipanteDTOUpdateStatusResponse.class);
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
