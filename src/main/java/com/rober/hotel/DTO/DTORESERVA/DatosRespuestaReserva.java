package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

public record DatosRespuestaReserva(
    Date fechaEntrada,
    Date fechaSalida,
    String formaP,
    String valor
) {}
