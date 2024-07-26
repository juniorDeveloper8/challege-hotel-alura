@ActualizarHuesped

Feature: Actualización de datos de huésped

  Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTcyMjAyODc0NX0.08tWrvmvDQQQ2qYDHza2t2mWek5XrpCndkAK6gyxw0o'

  Scenario Outline: Actualizar datos de un huésped existente solo nombres y apellido y comparar junto con los otros datos
    Given def id = <id>
    And path '/users/' + id
    And request
      """
      {
        "id": <id>,
        "nom": "<nuevo_nombre>",
        "ape": "<nuevo_apellido>"
      }
      """
    When method put
    Then status 200
    And match response.nom == "<nuevo_nombre>"
    And match response.ape == "<nuevo_apellido>"
    Examples:
      | id | nuevo_nombre | nuevo_apellido |
      | 1  | alekandra    | mendez         |
      | 2  | mosvish      | seranova       |

  Scenario Outline: Intentar actualizar datos de un huésped inexistente o ID no válido
    Given path '/users/<id>'
    And request
    """
    {
      "id": <id>,
      "nom": <nuevo_nombre>,
      "ape": <nuevo_apellido>
    }
    """
    When method put
    Then status <expected_status>

    Examples:
      | id | nuevo_nombre | nuevo_apellido | expected_status |
      | 20 | alekandra    | mendez         | 404             |


  Scenario Outline: Actualizar datos de un huésped con campos inválidos
    Given path "/users/<id>"
    And request
    """
    {
      "id": <id>,
      "nom": <nuevo_nombre>,
      "ape": <nuevo_apellido>
    }
    """
    When method put
    Then status <expected_status>

    Examples:
      | id | nuevo_nombre  | nuevo_apellido  | expected_status |
      | 5  | " "           | "NuevoApellido" | 400             |
      | 1  | "NuevoNombre" | " "             | 400             |



  Scenario: Intentar actualizar datos de un huésped inexistente
    Given path '/users/100'
    When method put
    Then status 403
