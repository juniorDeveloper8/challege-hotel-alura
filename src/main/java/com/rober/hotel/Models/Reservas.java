package com.rober.hotel.Models;

import java.sql.Date;

import com.rober.hotel.DTO.DTORESERVA.DatosActualizarReserva;
import com.rober.hotel.DTO.DTORESERVA.DatosRegistroReserva;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "reserva")
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private String valor;
    private String formaP;

    
    /**
     * manejo de estados
     */
    @Column(columnDefinition = "TINYINT(1)" )
    private boolean activo;

    /**
     * metodo de insertar los datos de reserva
     * @param datosRegistroReserva
     */
    public Reservas(DatosRegistroReserva datosRegistroReserva) {
        this.activo = true;
        this.fechaEntrada = datosRegistroReserva.fehaEntrada();
        this.fechaSalida = datosRegistroReserva.fehaSalida();
        this.valor = datosRegistroReserva.valor();
        this.formaP = datosRegistroReserva.formaP();
    }

    /**
     * actualizar reservas
     * @param datosActualizarReserva
     */
    public void actualizarDatosReserva(DatosActualizarReserva datosActualizarReserva) {
        if (datosActualizarReserva.fechaEntrada() != null ) {
            this.fechaEntrada = datosActualizarReserva.fechaEntrada();
        }
        if (datosActualizarReserva.fechaSalida() != null) {
            this.fechaSalida = datosActualizarReserva.fechaSalida();
        }
        if (datosActualizarReserva.formaP() != null ) {
            this.formaP = datosActualizarReserva.formaP();
        }
        if (datosActualizarReserva.valor() != null) {
            this.valor = datosActualizarReserva.valor();
        }
    }

    /**
     * delete logico
     */
    public void desactivarReserva(){
        this.activo = false;
    }

}
