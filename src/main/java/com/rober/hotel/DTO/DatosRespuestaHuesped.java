package com.rober.hotel.DTO;

import com.rober.hotel.Models.Nacionalidad;

import java.sql.Date;

public record DatosRespuestaHuesped(
    String nom,
    String ape,
    String documento,
    String phone,
    Nacionalidad nacionalidad,
    Date fechaN
) {
}

    
