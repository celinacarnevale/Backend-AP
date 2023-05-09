package com.portfolio.celinavillarroel.Controller;

import com.portfolio.celinavillarroel.Dto.dtoSkills;
import com.portfolio.celinavillarroel.Entity.Skills;
import com.portfolio.celinavillarroel.Security.Controller.Mensaje;
import com.portfolio.celinavillarroel.Service.SSkills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontend-ap-cv.web.apps")
@RequestMapping("/skills")
public class CSkills {
    @Autowired
    SSkills sSkills;
         
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id")int id){
        if(!sSkills.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Skills skills = sSkills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills){
        if(StringUtils.isBlank(dtoskills.getNombreSkill()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio, no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombreSkill(dtoskills.getNombreSkill()))
            return new ResponseEntity(new Mensaje("Esta skill ya existe."), HttpStatus.BAD_REQUEST);
        
        Skills skills = new Skills(dtoskills.getNombreSkill(),dtoskills.getPorcentaje());
        sSkills.save(skills);
        
        return new ResponseEntity(new Mensaje("Skill agregada con exito."), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskills){
        if(!sSkills.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombreSkill(dtoskills.getNombreSkill()) && sSkills.getByNombreSkill(dtoskills.getNombreSkill()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esta skill ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoskills.getNombreSkill()))
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        
        Skills skills = sSkills.getOne(id).get();
        skills.setNombreSkill(dtoskills.getNombreSkill());
        skills.setPorcentaje(dtoskills.getPorcentaje());
        
        sSkills.save(skills);
        return new ResponseEntity(new Mensaje("Skill actualizada exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sSkills.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        sSkills.delete(id);
        return new ResponseEntity(new Mensaje("Se ha borrado la skill correctamente"), HttpStatus.OK);
    }
}
