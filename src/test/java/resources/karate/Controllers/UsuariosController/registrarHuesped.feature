@RegistroHuesped

Feature: Pruebas para registrar huéspedes

  Background:
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'

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
    When method post
    Then status 201
    And match response ==
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

    Examples:
      | nombre | apellido | telefono   | documento  | nacionalidad | fechaN     |
      | Juan   | Pérez    | 7894657896 | 8529517410 | Ecuatoriano  | 2005-06-30 |
      | María  | López    | 987654321  | 123456789  | Ruso         | 2000-12-11 |


  Scenario: registrar un huésped con datos inválidos
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
    Then status 400

  Scenario Outline: Error al registrar con campos basios
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
    When method post
    Then status 400
    Examples:
      | nombre | apellido | telefono   | documento  | nacionalidad | fechaN     |
      | Jose   |          | 7894657896 | 8529517410 | Ecuatoriano  | 2005-06-30 |
      |        | masha    | 7894657896 |            | Ecuatoriano  | 2005-06-30 |