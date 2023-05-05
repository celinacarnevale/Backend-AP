package com.portfolio.celinavillarroel.Interface;

import com.portfolio.celinavillarroel.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //guardar un obj del tipo persona
    public void savePersona(Persona persona);
    
    //eliminar obj buscandolo por ID
    public void deletePersona(Long id);
    
    //Buscar una persona por Id
    public Persona findPersona(Long id);
}
