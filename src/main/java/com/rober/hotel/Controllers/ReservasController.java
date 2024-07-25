package com.rober.hotel.Controllers;

import java.net.URI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rober.hotel.DTO.DTORESERVA.DatosActualizarReserva;
import com.rober.hotel.DTO.DTORESERVA.DatosListarReserva;
import com.rober.hotel.DTO.DTORESERVA.DatosRegistroReserva;
import com.rober.hotel.DTO.DTORESERVA.DatosRespuestaReserva;
import com.rober.hotel.Interfaces.ReservaInterface;
import com.rober.hotel.Models.Reservas;

@RestController
@RequestMapping("/reservation")
public class ReservasController {

    @Autowired
    private ReservaInterface reservaInterface;

    /**
     * encapsulamos paro no exponer nuestro modelo y guradamo la reserva
     * 
     * @param datosRegistroReserva
     * @param uriComponentsBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity<DatosRespuestaReserva> registroReserva(
            @RequestBody DatosRegistroReserva datosRegistroReserva, UriComponentsBuilder uriComponentsBuilder) {
        Reservas reservas = reservaInterface.save(new Reservas(datosRegistroReserva));
        DatosRespuestaReserva datosRespuestaReserva = new DatosRespuestaReserva(
                // esto o retorna 200 o 201 para decir qsalio todo bien
                reservas.getFechaEntrada(),
                reservas.getFechaSalida(),
                reservas.getFormaP(),
                reservas.getValor());

        URI url = uriComponentsBuilder.path("/reservation/{id}").buildAndExpand(reservas.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaReserva);
    }

    /**
     * litando datos de reservas activas
     * 
     * @param pagination
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<DatosListarReserva>> listarReserva(@PageableDefault(size = 5) Pageable pagination) {

        return ResponseEntity.ok(reservaInterface.findByActivoTrue(pagination).map(DatosListarReserva::new));
    }

    /**
     * actulizaicon de la reserva
     * 
     * @param datosActualizarReserva
     * @return
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actulizarReserva(@RequestBody DatosActualizarReserva datosActualizarReserva) {
        Reservas reservas = reservaInterface.getReferenceById(datosActualizarReserva.id());
        reservas.actualizarDatosReserva(datosActualizarReserva);
        return ResponseEntity.ok(new DatosRespuestaReserva(
                reservas.getFechaEntrada(),
                reservas.getFechaSalida(),
                reservas.getFormaP(),
                reservas.getValor()));
    }

    /**
     * listar por id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaReserva> retornarDatosReserva(@PathVariable Integer id) {
        Reservas reservas = reservaInterface.getReferenceById(id);
        var datosReserva = new DatosRespuestaReserva(
                reservas.getFechaEntrada(),
                reservas.getFechaSalida(),
                reservas.getFormaP(),
                reservas.getValor());
        return ResponseEntity.ok(datosReserva);               
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarReserva(@PathVariable Integer id) {
        Reservas reservas = reservaInterface.getReferenceById(id);
        reservas.desactivarReserva();
        return ResponseEntity.noContent().build();
    }
}