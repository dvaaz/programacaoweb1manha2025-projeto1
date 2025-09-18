package com.senac.games.repository;

import com.senac.games.entities.Inscricao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Inscricao p SET p.status = -1 " +
            "WHERE p.id = :id")
    void apagarLogicoInscricao(@Param("id") Integer inscricaoId) ;

    @Query("SELECT p FROM Inscricao p WHERE p.status>=0")
    List<Inscricao> listarInscricaos();

    @Query("SELECT p FROM Inscricao p WHERE p.id = :id AND p.status>=0")
    Inscricao obterInscricaoPorID(@Param("id") Integer inscricaoId);
}
