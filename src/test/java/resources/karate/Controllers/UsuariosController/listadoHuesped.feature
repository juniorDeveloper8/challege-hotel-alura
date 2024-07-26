@ListadoHuesped

Feature: Pruebas de listado de Huespedes

   Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlcnRoQGdtYWlsLmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjIsImV4cCI6MTcyMTkzMDU0MH0._h2NqzDuVNV7gkE0Sf7qb8rBQ8KPd6H2QoGWE-GYLA0'

  Scenario: Obtener listado de huéspedes activos
    Given path "/users"
    When method get
    Then status 200

    # Verificar que la respuesta no sea nula
    And match response != null

    # Filtrar y verificar huéspedes activos
    * def huespedesActivos = karate.filter(response, function(huesped) { return huesped.activo == true })
      # Imprimir la lista de huéspedes activos para verificar
    * print 'Lista de huéspedes activos:', huespedesActivos

  Scenario: Obtener datos de un huésped por ID
    And def id = 2
    And path "/users/" + id
    When method get
    Then status 200d
