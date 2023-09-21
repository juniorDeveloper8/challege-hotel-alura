package com.rober.hotel.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarHuesped( 
    @NotNull
    Integer id,
    String nom,
    String ape,
    Date fechaN
) {}
