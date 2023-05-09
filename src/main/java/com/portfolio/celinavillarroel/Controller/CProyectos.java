package com.portfolio.celinavillarroel.Controller;

import com.portfolio.celinavillarroel.Dto.dtoProyectos;
import com.portfolio.celinavillarroel.Entity.Proyectos;
import com.portfolio.celinavillarroel.Security.Controller.Mensaje;
import com.portfolio.celinavillarroel.Service.SProyectos;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://frontend-ap-cv.web.app")

public class CProyectos {
    @Autowired
    SProyectos sProyectos;
         
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id")int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtoproyectos){
        if(StringUtils.isBlank(dtoproyectos.getNombreProy()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio, no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        
        if(sProyectos.existsByNombreProy(dtoproyectos.getNombreProy()))
            return new ResponseEntity(new Mensaje("Este proyecto ya existe."), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = new Proyectos(dtoproyectos.getNombreProy(),dtoproyectos.getDescripcionProy(), dtoproyectos.getLink(), dtoproyectos.getFecha());
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto agregado con exito."), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyectos dtoproyectos){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sProyectos.existsByNombreProy(dtoproyectos.getNombreProy()) && sProyectos.getByNombreProy(dtoproyectos.getNombreProy()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Este proyecto ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoproyectos.getNombreProy()))
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombreProy(dtoproyectos.getNombreProy());
        proyectos.setDescripcionProy(dtoproyectos.getDescripcionProy());
        proyectos.setLink(dtoproyectos.getLink());
        proyectos.setFecha(dtoproyectos.getFecha());
        
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proy actualizado exitosamente."), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Se ha borrado el proyecto correctamente"), HttpStatus.OK);
    }
}
