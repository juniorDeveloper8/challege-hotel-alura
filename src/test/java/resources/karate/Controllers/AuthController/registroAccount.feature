@RegistroAccount

Feature: Creacion de cuenta para usar la api reservas de hotel

  Background:

    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/login/create'

  @testPOST
  Scenario Outline: crear una cuenta con datos validos y obtener el codigo de respuesta <status_code> exitoso
    Given url urlBase + urlPath
    And request
  """{
  "login": "<username>",
  "clave": "<password>"
  }"""
    When method POST
    * print response
    Then response.status == <status_code>
    Examples:
      | username          | password   | status_code |
      | roberth@gmail.com | roberth852 | 201         |
      | testin852         | alonso14   | 201         |
      | rober@test.com    | 852rober   | 201         |

  @testPOST
  Scenario Outline: creacion de cuenta con datos invalidos
    Given url urlBase + urlPath
    And  request { 'login': <username>, 'clave': <password> }
    When method POST
    * print response
    Then response.status == <status_code>

    Examples:
      | username  | password   | status_code |
      | " "       | roberth852 | 400         |
      | testin852 | " "        | 400         |