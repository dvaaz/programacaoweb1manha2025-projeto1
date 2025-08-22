package com.senac.games.repository;

import com.senac.games.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante,Integer> {
}
