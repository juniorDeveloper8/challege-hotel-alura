package com.rober.hotel.DTO;

import java.sql.Date;

import com.rober.hotel.Models.Nacionalidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroHuesped(
    @NotBlank
    String nom,
    @NotBlank
    String ape,
    @NotBlank
    Date fechaN,
    @NotBlank
    String phone,
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String documento,
    @NotBlank
    Nacionalidad nacionalidad
  
) {}
