package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

public record DatosRespuestaReserva(
    Date fechaEntrada,
    Date fechaSAlida,
    String formaP,
    String valor
) {}
