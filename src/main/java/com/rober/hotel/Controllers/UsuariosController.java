package com.rober.hotel.Controllers;

import java.net.URI;
import java.util.List;

import com.rober.hotel.DTO.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;

import com.rober.hotel.Interfaces.HuespedInterface;
import com.rober.hotel.Models.Huesped;

@RestController
@RequestMapping("/users")
public class UsuariosController {

    @Autowired
    private HuespedInterface huespedInterface;

    /**
     * encapsulamos para no exponer nuetro modelo
     *
     * @param datosRegistroHuesped
     * @param uriComponentsBuilder
     * @return
     */

    @PostMapping
    public ResponseEntity<DatosRespuestaHuesped> registrarHuespedes(
            @RequestBody DatosRegistroHuesped datosRegistroHuesped, UriComponentsBuilder uriComponentsBuilder) {

        if (datosRegistroHuesped.nom().isBlank() || datosRegistroHuesped.ape().isBlank() || datosRegistroHuesped.documento().isBlank() || datosRegistroHuesped.phone().isBlank() || datosRegistroHuesped.nacionalidad() == null || datosRegistroHuesped.fechaN() == null) {
            return ResponseEntity.badRequest().build();
        }

        Huesped huesped = huespedInterface.save(new Huesped(datosRegistroHuesped));
        DatosRespuestaHuesped datosRespuestaHuesped = new DatosRespuestaHuesped(

                huesped.getNom(),
                huesped.getApe(),
                huesped.getDocumento(),
                huesped.getPhone().toString(),
                huesped.getNacionalidad(),
                huesped.getFechaN()
        );

        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(huesped.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaHuesped);

    }

    /**
     * tamos tralledon datos para paginas
     *d
     * @return
     */

    @GetMapping
    public ResponseEntity<?> listadoHuesped() {

        List<DatosListarHuesped> datosListarHuespedList = huespedInterface.findByActivoTrue().stream()
                .map(huesped -> DatosListarHuesped.builder()
                        .id(huesped.getId())
                        .nom(huesped.getNom())
                        .ape(huesped.getApe())
                        .fechaN(huesped.getFechaN())
                        .phone(huesped.getPhone())
                        .build())
                .toList();
        return ResponseEntity.ok(datosListarHuespedList);

    }

    /**
     * actualizamos al huesped
     *
     * @param datosActualizarHuesped
     * @return
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarHuesped(@RequestBody DatosActualizarHuesped datosActualizarHuesped, @PathVariable Integer id ) {
        // Verificar que los campos nom y ape no estén vacíos
        if (datosActualizarHuesped.nom().isBlank() || datosActualizarHuesped.ape().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Huesped huesped = huespedInterface.getReferenceById(datosActualizarHuesped.id());
        huesped.actualizarDatos(datosActualizarHuesped);
        return ResponseEntity.ok(new DatosRespuestaActualizarHuesped(
                huesped.getNom(),
                huesped.getApe().toString())
        );
    }

    /**
     * elminamos de forma logica al huesped de forma logica
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eleminarHuesped(@PathVariable Integer id) {
        Huesped huesped = huespedInterface.getReferenceById(id);
        huesped.desactivarHuesped();
        return ResponseEntity.noContent().build();
    }

    /**
     * listamos a un solo usuari me supongo
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaHuesped> retornarDatosHuesped(@PathVariable Integer id) {
        Huesped huesped = huespedInterface.getReferenceById(id);
        var datosHuesped = new DatosRespuestaHuesped(
                huesped.getNom(),
                huesped.getApe(),
                huesped.getDocumento(),
                huesped.getPhone().toString(),
                huesped.getNacionalidad(),
                huesped.getFechaN());
        return ResponseEntity.ok(datosHuesped);
    }

}
