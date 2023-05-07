
package com.portfolio.celinavillarroel.Repository;

import com.portfolio.celinavillarroel.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RSkills extends JpaRepository<Skills, Integer>{
    public Optional<Skills> findByNombreSkill(String nombreSkill);
    
    public boolean existsByNombreSkill(String nombreSkill);
}
