package com.senac.games.service;

import com.senac.games.entities.dto.request.InscricaoDTORequest;
import com.senac.games.entities.dto.request.InscricaoDTOUpdateStatusRequest;
import com.senac.games.entities.dto.response.InscricaoDTOResponse;
import com.senac.games.entities.dto.response.InscricaoDTOUpdateStatusResponse;
import com.senac.games.entities.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
  private final InscricaoRepository inscricaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = modelMapper.map(inscricaoDTORequest, Inscricao.class);

        Inscricao inscricaoSave = this.inscricaoRepository.save(inscricao);
        InscricaoDTOResponse inscricaoDTOResponse = modelMapper.map(inscricaoSave, InscricaoDTOResponse.class);

        return inscricaoDTOResponse;
    }

    public List<Inscricao> listarInscricaos() { return this.inscricaoRepository.listarInscricaos(); }

    public Inscricao listarInscricaoPorId(Integer inscricaoId) {
        Inscricao inscricao = this.inscricaoRepository.obterInscricaoPorID(inscricaoId);
        if (inscricao != null) {
            return this.inscricaoRepository.obterInscricaoPorID(inscricaoId);
        } else return null;
    }

    public InscricaoDTOUpdateStatusResponse atualizarStatusInscricao(Integer inscricaoId, InscricaoDTOUpdateStatusRequest inscricaoDTOUpdateStatusRequest) {
        Inscricao inscricao = this.listarInscricaoPorId(inscricaoId);
        if (inscricao != null) {
            inscricao.setStatus(inscricaoDTOUpdateStatusRequest.getStatus());

            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelMapper.map(tempResponse, InscricaoDTOUpdateStatusResponse.class);
        } else return null;
    }

    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = this.listarInscricaoPorId(inscricaoId);

        if (inscricao != null) {
            modelMapper.map(inscricaoDTORequest, inscricao);
            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelMapper.map(tempResponse, InscricaoDTOResponse.class);

        } else return null;
    }

    public void apagarInscricao(Integer inscricaoId){
        this.inscricaoRepository.apagarLogicoInscricao(inscricaoId);
    }
}
