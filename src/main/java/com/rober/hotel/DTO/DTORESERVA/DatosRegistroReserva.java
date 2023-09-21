package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroReserva(
    @NotBlank
    Date fehaEntrada,
    @NotBlank
    Date fehaSalida,
    @NotBlank
    String valor,
    @NotBlank
    String formaP
) {}
