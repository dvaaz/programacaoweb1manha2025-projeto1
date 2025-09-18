package com.senac.games.repository;

import com.senac.games.entities.Patrocinador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Patrocinador p SET p.status = -1 " +
            "WHERE p.id = :id")
    void apagarLogicoPatrocinador(@Param("id") Integer patrocinadorId) ;

    @Query("SELECT p FROM Patrocinador p WHERE p.status>=0")
    List<Patrocinador> listarPatrocinadors();

    @Query("SELECT p FROM Patrocinador p WHERE p.id = :id AND p.status>=0")
    Patrocinador obterPatrocinadorPorID(@Param("id") Integer patrocinadorId);
}
