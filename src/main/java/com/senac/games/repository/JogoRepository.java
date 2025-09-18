package com.senac.games.repository;

import com.senac.games.entities.Jogo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    // Criação de HQL que mistura SQL com OOP
    @Modifying
    @Transactional
    @Query("UPDATE Jogo p SET p.status = -1 " +
            "WHERE p.id = :id")
    void apagarLogicoJogo(@Param("id") Integer jogoId) ;

    @Query("SELECT p FROM Jogo p WHERE p.status>=0")
    List<Jogo> listarJogos();

    @Query("SELECT p FROM Jogo p WHERE p.id = :id AND p.status>=0")
    Jogo obterJogoPorID(@Param("id") Integer jogoId);
}
