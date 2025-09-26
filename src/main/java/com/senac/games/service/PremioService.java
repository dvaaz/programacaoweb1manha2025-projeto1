package com.senac.games.service;

import com.senac.games.entities.dto.request.PremioDTORequest;
import com.senac.games.entities.dto.request.PremioDTOUpdateStatusRequest;
import com.senac.games.entities.dto.response.PremioDTOResponse;
import com.senac.games.entities.dto.response.PremioDTOUpdateStatusResponse;
import com.senac.games.entities.Premio;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {
    private final PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);

        Premio premioSave = this.premioRepository.save(premio);
        PremioDTOResponse premioDTOResponse = modelMapper.map(premioSave, PremioDTOResponse.class);

        return premioDTOResponse;
    }

    public List<Premio> listarPremios() { return this.premioRepository.listarPremios(); }

    public Premio listarPremioPorId(Integer premioId) {
        Premio premio = this.premioRepository.obterPremioPorID(premioId);
        if (premio != null) {
            return this.premioRepository.obterPremioPorID(premioId);
        } else return null;
    }

    public PremioDTOUpdateStatusResponse atualizarStatusPremio(Integer premioId, PremioDTOUpdateStatusRequest premioDTOUpdateStatusRequest) {
        Premio premio = this.listarPremioPorId(premioId);
        if (premio != null) {
            premio.setStatus(premioDTOUpdateStatusRequest.getStatus());

            Premio tempResponse = premioRepository.save(premio);
            return modelMapper.map(tempResponse, PremioDTOUpdateStatusResponse.class);
        } else return null;
    }

    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest) {
        Premio premio = this.listarPremioPorId(premioId);

        if (premio != null) {
            modelMapper.map(premioDTORequest, premio);
            Premio tempResponse = premioRepository.save(premio);
            return modelMapper.map(tempResponse, PremioDTOResponse.class);

        } else return null;
    }

    public void apagarPremio(Integer premioId){
        this.premioRepository.apagarLogicoPremio(premioId);
    }
}
