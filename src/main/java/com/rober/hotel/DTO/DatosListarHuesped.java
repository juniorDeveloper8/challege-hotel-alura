package com.rober.hotel.DTO;

import java.sql.Date;

import com.rober.hotel.Models.Huesped;

public record DatosListarHuesped(
    Integer id,
    String nom,
    String ape,
    Date fechaN,
    String phone,
    String nacionalidad
) {

    /**
     * listar a lod huespedes
     * @param huesped
     */
    public DatosListarHuesped(Huesped huesped){

        this(
            huesped.getId(),
            huesped.getNom(),
            huesped.getApe(),
            huesped.getFechaN(),
            huesped.getPhone(),
            huesped.getNacionalidad().toString()
    
        );
    }
}
