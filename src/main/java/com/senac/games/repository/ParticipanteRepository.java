package com.senac.games.repository;

import com.senac.games.entities.Participante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Integer> {
  // Criação de HQL que mistura SQL com OOP
  @Modifying
  @Transactional
  @Query("UPDATE Participante p SET p.status = -1 " +
      "WHERE p.id = :id")
  void apagarLogicoParticipante(@Param("id") Integer participanteId) ;

  @Query("SELECT p FROM Participante p WHERE p.status>=0")
  List<Participante> listarParticipantes();

  @Query("SELECT p FROM Participante p WHERE p.id = :id AND p.status>=0")
  Participante obterParticipantePorID(@Param("id") Integer participanteId);
}
