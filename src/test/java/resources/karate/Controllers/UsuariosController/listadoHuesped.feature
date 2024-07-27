@ListadoHuesped

Feature: Pruebas de listado de Huespedes

   Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjMsImV4cCI6MTcyMjExMTg1M30.Kil23-T1XFAbvPnYtsJr21K898wi2CPW8gjRvMRSU48'

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
    Then status 200
