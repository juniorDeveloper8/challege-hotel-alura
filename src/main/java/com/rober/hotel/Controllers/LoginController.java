package com.rober.hotel.Controllers;

import com.rober.hotel.DTO.DTOUSER.DatosRegistroUserLogin;
import com.rober.hotel.Interfaces.UsuariosInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rober.hotel.DTO.DTOUSER.DatosJWTToken;
import com.rober.hotel.DTO.DTOUSER.DatosUsuaroiAutenticacion;
import com.rober.hotel.Models.Users.Usuarios;
import com.rober.hotel.Services.TokenService;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuariosInterface usuariosInterface;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosUsuaroiAutenticacion datosUsuaroiAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosUsuaroiAutenticacion.login(),
                datosUsuaroiAutenticacion.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuarios) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUserLogin datosRegistroUserLogin) {
        if (datosRegistroUserLogin.login().isBlank() || datosRegistroUserLogin.clave().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        // Encriptar la contraseña utilizando BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String claveEncriptada = passwordEncoder.encode(datosRegistroUserLogin.clave());

        // Construir la entidad Usuarios con la contraseña encriptada
        Usuarios usuario = Usuarios.builder()
                .login(datosRegistroUserLogin.login())
                .clave(claveEncriptada) // Guardar la contraseña encriptada
                .build();

        // Guardar el usuario en la base de datos
        Usuarios usuarioGuardado = usuariosInterface.save(usuario);

        // Retornar una respuesta con código 201 Created y la URI del recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioGuardado.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

 }
