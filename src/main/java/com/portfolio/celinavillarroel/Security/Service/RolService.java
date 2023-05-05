package com.portfolio.celinavillarroel.Security.Service;

import com.portfolio.celinavillarroel.Security.Entity.Rol;
import com.portfolio.celinavillarroel.Security.Enums.RolNombre;
import com.portfolio.celinavillarroel.Security.Repository.iRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
