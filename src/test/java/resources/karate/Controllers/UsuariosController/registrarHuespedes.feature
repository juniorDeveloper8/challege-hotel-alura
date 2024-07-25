@RegistroHuesped

Feature: Pruebas para registrar huéspedes

  Background:
    * configure headers = { 'Content-Type': 'application/json' }

  Scenario Outline: Registrar un nuevo huésped exitosamente
    Given url "http://localhost:8080"
    And path "/users"
    And request
      """
      {
        "nom": "<nombre>",
        "ape": "<apellido>",
        "phone": "<telefono>",
        "documento": "<documento>",
        "nacionalidad": "<nacionalidad>",
        "fechaN": "<fechaN>"
      }
      """
    And header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTcyMTg5MjE3Mn0.R-6dsWhj2Om8HRtrThqssMr0X1ykJplkaUMXdZfd8Tg'
    When method post
    Then status 201
  #  And match response ==
  #    """
  #    {
  #      "nom": "<nombre>",
  #      "ape": "<apellido>",
  #      "phone": "<telefono>",
  #      "documento": "<documento>",
  #      "nacionalidad": "<nacionalidad>",
  #      "fechaN": "<fechaN>"
  #    }
  #    """

    Examples:
      | nombre | apellido | telefono   | documento  | nacionalidad | fechaN     |
      | Juan   | Pérez    | 7894657896 | 8529517410 | Ecuatoriano  | 2005-06-30 |
      | María  | López    | 987654321  | 123456789  | Ruso         | 2000-12-11 |


  Scenario: Error al registrar un huésped con datos inválidos
    Given url "http://localhost:8080"
    And path "/users"
    And request
      """
      {
        "nom": "",
        "ape": "González",
        "phone": "987654321",
        "documento": "1234567890",
        "nacionalidad": "Ecuatoriano",
        "fechaN": "2000-01-01"
      }
      """
    When method post
    Then status 403