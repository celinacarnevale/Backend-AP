package com.portfolio.celinavillarroel.Repository;

import com.portfolio.celinavillarroel.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPersonaRepository extends JpaRepository<Persona,Long> {
    
}
