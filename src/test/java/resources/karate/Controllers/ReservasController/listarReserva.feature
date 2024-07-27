@ListarReserva

Feature: Pruebas para listar los datos de las reservas

  Background:
    * configure headers = { 'Content-Type': 'application/json ' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'
    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/reservation'
    * def param = '/2'

  Scenario: Obtener las Reservas Activas
    Given url urlBase + urlPath
    When method GET
    Then status 200
    And match response != null
    * def reservasActivas = karate.filter(response, function(reservas) { return reservas.activo == true })
    * print 'Lista de reservas activas: ', reservasActivas

  Scenario: Obtener datos de reserva por ID
    Given url urlBase + urlPath + param
    When method GET
    Then status 200

  Scenario: Error al obtener los datos por url incorrecta o Id inexistente
    Given url urlBase + param
    When method GET
    Then karate.match(responseStatus, [400, 404])