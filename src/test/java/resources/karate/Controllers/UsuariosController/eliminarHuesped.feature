@EliminarHuesped

Feature: Eliminación de huésped

  Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Accept': '*/*', 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlcnRoQGdtYWlsLmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTcyMTk0NjM5MX0.SMVao9L2jREtl2-c0Nt_S79FdBI5-EDs2qAqtQuD4OY'

  Scenario: Eliminar un huésped existente
    Given path '/users/2'
    When method delete
    Then status 204

  Scenario: Intentar eliminar un huésped inexistente
    Given path '/users/100'
    When method delete
    Then status 404
