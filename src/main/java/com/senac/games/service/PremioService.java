package com.senac.games.service;

import com.senac.games.entities.Premio;
import com.senac.games.repository.PremioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {
    private final PremioRepository premioRepository;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public List<Premio> listarPremios() {
        return this.premioRepository.findAll();

    }
}
