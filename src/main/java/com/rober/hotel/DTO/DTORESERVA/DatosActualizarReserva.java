package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarReserva(
    
    @NotNull
    Integer id,
    Date fechaEntrada,
    Date fechaSalida,
    String valor,
    String formaP
){}
