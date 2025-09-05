package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.request.PatrocinadorDTOUpdateStatusRequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateStatusResponse;
import com.senac.games.entities.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {
    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    @Transactional
    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);

        Patrocinador patrocinadorSave = this.patrocinadorRepository.save(patrocinador);
        PatrocinadorDTOResponse patrocinadorDTOResponse = modelMapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);

        return patrocinadorDTOResponse;
    }

    public List<Patrocinador> listarPatrocinadors() { return this.patrocinadorRepository.listarPatrocinadors(); }

    public Patrocinador listarPatrocinadorPorId(Integer patrocinadorId) {
        Patrocinador patrocinador = this.patrocinadorRepository.obterPatrocinadorPorID(patrocinadorId);
        if (patrocinador != null) {
            return this.patrocinadorRepository.obterPatrocinadorPorID(patrocinadorId);
        } else return null;
    }

    @Transactional
    public PatrocinadorDTOUpdateStatusResponse atualizarStatusPatrocinador(Integer patrocinadorId, PatrocinadorDTOUpdateStatusRequest patrocinadorDTOUpdateStatusRequest) {
        Patrocinador patrocinador = this.listarPatrocinadorPorId(patrocinadorId);
        if (patrocinador != null) {
            patrocinador.setStatus(patrocinadorDTOUpdateStatusRequest.getStatus());

            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(tempResponse, PatrocinadorDTOUpdateStatusResponse.class);
        } else return null;
    }

    @Transactional
    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = this.listarPatrocinadorPorId(patrocinadorId);

        if (patrocinador != null) {
            modelMapper.map(patrocinadorDTORequest, patrocinador);
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelMapper.map(tempResponse, PatrocinadorDTOResponse.class);

        } else return null;
    }

    @Transactional
    public void apagarPatrocinador(Integer patrocinadorId){
        this.patrocinadorRepository.apagarLogicoPatrocinador(patrocinadorId);
    }
}
