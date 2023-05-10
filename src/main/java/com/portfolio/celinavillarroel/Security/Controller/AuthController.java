package com.portfolio.celinavillarroel.Security.Controller;

import com.portfolio.celinavillarroel.Security.Dto.JwtDto;
import com.portfolio.celinavillarroel.Security.Dto.LoginUsuario;
import com.portfolio.celinavillarroel.Security.Dto.NuevoUsuario;
import com.portfolio.celinavillarroel.Security.Entity.Rol;
import com.portfolio.celinavillarroel.Security.Entity.Usuario;
import com.portfolio.celinavillarroel.Security.Enums.RolNombre;
import com.portfolio.celinavillarroel.Security.JWT.JwtProvider;
import com.portfolio.celinavillarroel.Security.Service.RolService;
import com.portfolio.celinavillarroel.Security.Service.UsuarioService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://frontend-ap-cv.web.app")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos llenados incorrectamente o email invalido"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
                    
                    if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Este email ya se encuentra en uso"), HttpStatus.BAD_REQUEST);
                    
                    Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
 
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            
            if(nuevoUsuario.getRoles().contains("admin"))
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            usuario.setRoles(roles);
            usuarioService.save(usuario);
            
            return new ResponseEntity(new Mensaje("Usuario guardado!"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos llenados de forma incorrecta"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNombreUsuario(), loginUsuario.getPassword()
        ));
         
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto  = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
