@ActualizarReserva
Feature: Pruebas para actualizar una reserva

  Background:
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'
    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/reservation'

  Scenario Outline: Actualizar reserva existente + comparación de datos con datos válidos
    Given def id = <id>
    And url urlBase + urlPath + '/' + id
    And request
    """
    {
      "id": <id>,
      "fechaEntrada": "<fechaEntrada>",
      "fechaSalida": "<fechaSalida>",
      "valor": "<valor>",
      "formaP": "<formaPago>"
    }
    """
    When method PUT
    Then status 200
    And match response.fechaEntrada == "<fechaEntrada>"
    And match response.fechaSalida == "<fechaSalida>"
    And match response.valor == "<valor>"
    And match response.formaP == "<formaPago>"
    Examples:
      | id | fechaEntrada | fechaSalida | valor | formaPago |
      | 1  | 2024-09-20   | 2025-01-27  | 1500  | tarjeta   |
      | 2  | 2024-10-27   | 2024-11-27  | 200   | tarjeta   |

  Scenario Outline: Actualizar reserva de ID inexistente o ID no válido
    Given def id = <id>
    And url urlBase + urlPath + '/' + id
    And request
    """
    {
      "id": <id>,
      "fechaEntrada": "<fechaEntrada>",
      "fechaSalida": "<fechaSalida>",
      "valor": "<valor>",
      "formaP": "<formaPago>"
    }
    """
    When method PUT
    Then karate.match(responseStatus, [400, 404])
    Examples:
      | id | fechaEntrada | fechaSalida | valor | formaPago |
      | 10 | 2024-09-20   | 2025-01-27  | 1500  | tarjeta   |
      | 20 | 2024-10-27   | 2024-11-27  | 200   | tarjeta   |

  Scenario Outline: Actualizar reserva con campos inválidos o vacíos
    Given def id = <id>
    And url urlBase + urlPath + '/' + id
    And request
    """
    {
      "id": <id>,
      "fechaEntrada": "<fechaEntrada>",
      "fechaSalida": "<fechaSalida>",
      "valor": "<valor>",
      "formaP": "<formaPago>"
    }
    """
    When method PUT
    Then karate.match(responseStatus, [400, 404])
    Examples:
      | id | fechaEntrada | fechaSalida | valor | formaPago |
      | 1  | sjdf;sd      | 2025-01-27  | asdf  | tarjeta   |
      | 2  |              | 2024-11-27  | 200   |           |
