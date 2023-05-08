
package com.portfolio.celinavillarroel.Repository;

import com.portfolio.celinavillarroel.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RProyectos extends JpaRepository<Proyectos, Integer>{
    public Optional<Proyectos> findByNombreProy(String nombreProy);
    
    public boolean existsByNombreProy(String nombreProy);
}
