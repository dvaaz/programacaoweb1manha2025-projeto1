package com.senac.games.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.senac.games.entities.Premio;
@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {

}
