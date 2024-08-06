Feature: Crear cuenta de usuario

  @CrearCuenta
  Scenario: crear cuenta
    When creo la cuenta con login "robert787", clave "noseopo"
    Then el codigo de respuesta es 200
    And el type es "unknown