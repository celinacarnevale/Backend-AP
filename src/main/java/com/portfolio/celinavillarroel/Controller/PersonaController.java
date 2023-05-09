package com.portfolio.celinavillarroel.Controller;

import com.portfolio.celinavillarroel.Dto.dtoPersona;
import com.portfolio.celinavillarroel.Entity.Persona;
import com.portfolio.celinavillarroel.Security.Controller.Mensaje;
import com.portfolio.celinavillarroel.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "https://frontend-ap-cv.web.app")

public class PersonaController {
    @Autowired
    ImpPersonaService sPersona;
         
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopers){
        if(StringUtils.isBlank(dtopers.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio, no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        
        if(sPersona.existsByNombre(dtopers.getNombre()))
            return new ResponseEntity(new Mensaje("Esta persona ya existe."), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona(dtopers.getNombre(),dtopers.getApellido(), dtopers.getDescripcion());
        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona agregada con exito."), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopers){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sPersona.existsByNombre(dtopers.getNombre()) && sPersona.getByNombre(dtopers.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtopers.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        
        Persona persona = sPersona.getOne(id).get();
        persona.setNombre(dtopers.getNombre());
        persona.setApellido(dtopers.getApellido());
        persona.setDescripcion(dtopers.getDescripcion());
        
        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada exitosamente."), HttpStatus.OK);
    } 
}
