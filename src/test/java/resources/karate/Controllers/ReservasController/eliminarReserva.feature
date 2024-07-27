@EliminarReserva

Feature:Eliminar una reserva

  Background:
    * configure headers = { 'Content-Type': 'application/json ' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'
    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/reservation'
    * def param = '/2'
    * def paramInvalid = '/100'

  Scenario: Eliminar una reserva exitente
    Given url urlBase + urlPath + param
    When method DELETE
    Then status 204

  Scenario: Eliminar una reserva inexistente
    Given url urlBase + urlPath + paramInvalid
    When method DELETE
    Then status 404
