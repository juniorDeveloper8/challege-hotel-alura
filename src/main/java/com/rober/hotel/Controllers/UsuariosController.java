package com.rober.hotel.Controllers;

import java.net.URI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;

import com.rober.hotel.DTO.DatosActualizarHuesped;
import com.rober.hotel.DTO.DatosListarHuesped;
import com.rober.hotel.DTO.DatosRegistroHuesped;
import com.rober.hotel.DTO.DatosRespuestaHuesped;
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
        Huesped huesped = huespedInterface.save(new Huesped(datosRegistroHuesped));
        DatosRespuestaHuesped datosRespuestaHuesped = new DatosRespuestaHuesped(
                // este tiene q retornar 201 que significa creado
                huesped.getId(),
                huesped.getNom(),
                huesped.getApe(),
                huesped.getPhone(),
                huesped.getNacionalidad().toString(),
                huesped.getFechaN());

        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(huesped.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaHuesped);

    }

    /**
     * 
     * tamos tralledon datos para paginas
     * 
     * @param paginacion
     * @return
     */

    @GetMapping
    public ResponseEntity<Page<DatosListarHuesped>> listadoHuesped(@PageableDefault(size = 5) Pageable paginacion) {

        return ResponseEntity.ok(huespedInterface.findByActivoTrue(paginacion).map(DatosListarHuesped::new));
    }

    /**
     * actualizamos al huesped
     * 
     * @param datosActualizarHuesped
     * @return
     */
    @PutMapping
    @Transactional
    public ResponseEntity actualizarHuesped(@RequestBody DatosActualizarHuesped datosActualizarHuesped) {
        Huesped huesped = huespedInterface.getReferenceById(datosActualizarHuesped.id());
        huesped.actualizarDatos(datosActualizarHuesped);
        return ResponseEntity.ok(new DatosRespuestaHuesped(
                huesped.getId(),
                huesped.getNom(),
                huesped.getApe(),
                huesped.getPhone(),
                huesped.getNacionalidad().toString(),
                huesped.getFechaN()));
    }

    /**
     * elminamos de forma logica al huesped q no deveria pero bueno
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
                huesped.getId(),
                huesped.getNom(),
                huesped.getApe(),
                huesped.getPhone(),
                huesped.getNacionalidad().toString(),
                huesped.getFechaN());
        return ResponseEntity.ok(datosHuesped);
    }

}
