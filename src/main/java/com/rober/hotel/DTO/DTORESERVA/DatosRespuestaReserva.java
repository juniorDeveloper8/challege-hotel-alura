package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

public record DatosRespuestaReserva(
    Integer id,
    Date fechaEntrada,
    Date fechaSAlida,
    String formaP,
    String valor
) {}
