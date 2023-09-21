package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

import com.rober.hotel.Models.Reservas;

public record DatosListarReserva(
    Integer id,
    Date fechaEntrada,
    Date fechaSalida,
    String formaP,
    String valor
) {
    /**
     * listar reservas
     * @param reservas
     */
    public DatosListarReserva(Reservas reservas){
        this(
            reservas.getId(), 
            reservas.getFechaEntrada(), 
            reservas.getFechaSalida(), 
            reservas.getFormaP(), 
            reservas.getValor()
        );
    }
}
