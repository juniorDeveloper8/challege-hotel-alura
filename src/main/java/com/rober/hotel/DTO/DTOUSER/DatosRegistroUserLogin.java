package com.rober.hotel.DTO.DTOUSER;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUserLogin(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}
