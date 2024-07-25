@EliminarHuesped

Feature: Eliminación de huésped

  Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Accept': '*/*', 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTcyMTg4NTI5N30.XPB8OYMKo9Z_Mzh5xepWPtxl20S0Amrb4wlaR8d9tc8'

  Scenario: Eliminar un huésped existente
    Given path '/users/2'
    When method delete
    Then status 204

  Scenario: Intentar eliminar un huésped inexistente
    Given path '/users/100'
    When method delete
    Then status 404
