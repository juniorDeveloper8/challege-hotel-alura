@RegistarReserva

Feature: Pruebas para registrar una reserva

  Background:
    * configure headers = { 'Content-Type': 'application/json ' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'
    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/reservation'

  Scenario Outline: Registrar una reserva exitosamente
    Given url urlBase + urlPath
    And request
     """
     {
        "fechaEntrada": "<fechaEntrada>",
        "fechaSalida": "<fechaSalida>",
        "valor": "<valor>",
        "formaP": "<formaPago>"
     }
     """
    When method POST
    * print response
    Then response.status == <status_code>

    Examples:
      | fechaEntrada | fechaSalida | valor | formaPago | status_code |
      | 2024-07-20   | 2024-07-30  | 150   | tarjeta   | 201         |
      | 2024-07-27   | 2024-08-01  | 20    | efectivo  | 201         |

  Scenario Outline: Registrar reserva con datos invalidos o campos basios
    Given url urlBase + urlPath
    And request
      """
     {
        "fechaEntrada": "<fechaEntrada>",
        "fechaSalida": "<fechaSalida>",
        "valor": "<valor>",
        "formaP": "<formaPago>"
     }
     """
    When method POST
    * print response
    Then response.status == <status_code>

    Examples:
      | fechaEntrada | fechaSalida | valor | formaPago | status_code |
      | asdafja;     |             | 150   | tarjeta   | 400;     |
      | 2024-07-27   | 2024-08-01  |       | efectivo  | 400         |

