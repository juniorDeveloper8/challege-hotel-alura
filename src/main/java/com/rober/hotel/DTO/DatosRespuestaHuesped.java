package com.rober.hotel.DTO;

import java.sql.Date;


public record DatosRespuestaHuesped(
    Integer id,
    String nom,
    String ape,
    String phone,
    String documento,
    Date fechaN
) {}

    
