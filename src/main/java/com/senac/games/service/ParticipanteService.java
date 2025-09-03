package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.request.ParticipanteDTOUpdateStatusRequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateStatusResponse;
import com.senac.games.entities.Participante;
import com.senac.games.repository.ParticipanteRepository;
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

    public List<Participante> listarParticipantes() {
        return this.participanteRepository.findAll();
    }

    public Participante listarPorParticipanteId(Integer participanteId) {
            return this.participanteRepository.findById(participanteId).orElse(null);
    }

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

  public ParticipanteDTOUpdateStatusResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTOUpdateStatusRequest participanteDTOUpdateRequest) {
      Participante participante = this.listarPorParticipanteId(participanteId);

      if (participante != null) {
        //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
        //que é compatível com o repository para atualizar
        modelMapper.map(participanteDTOUpdateRequest,participante);

        Participante tempResponse = participanteRepository.save(participante);
        return modelMapper.map(tempResponse, ParticipanteDTOUpdateStatusResponse.class);

      } else  {
      return null;
      }

  }
}
