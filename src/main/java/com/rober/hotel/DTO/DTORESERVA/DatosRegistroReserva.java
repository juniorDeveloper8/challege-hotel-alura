package com.rober.hotel.DTO.DTORESERVA;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroReserva(
    @NotBlank
    Date fechaEntrada,
    @NotBlank
    Date fechaSalida,
    @NotBlank
    String valor,
    @NotBlank
    String formaP
) {}
