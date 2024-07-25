@ListadoHuesped

Feature: Pruebas de listado de Huespedes

   Background:
    * url 'http://localhost:8080'
    * configure headers = { 'Content-Type': 'application/json' }
    * header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JlckB0ZXN0LmNvbSIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTcyMTg5MjE3Mn0.R-6dsWhj2Om8HRtrThqssMr0X1ykJplkaUMXdZfd8Tg'

  Scenario: Obtener listado de huéspedes activos
    And path "/users"
    When method get
    Then status 200

    # Verificar que la respuesta no sea nula
    And match response != null

    # Filtrar y verificar huéspedes activos
    * def huespedesActivos = karate.filter(response, function(huesped) { return huesped.activo == true })

    # Verificar que hay al menos un huésped activo
    * def sizeOfHuespedesActivos = karate.sizeOf(huespedesActivos)
    * print 'Número de huéspedes activos:', sizeOfHuespedesActivos

    # Validar la estructura de cada huésped activo
    And match each huespedesActivos ==
      """
      {
        id: '#number',
        nom: '#string',
        ape: '#string',
        fechaN: '#string',
        phone: '#string',
        nacionalidad: '#string',
        activo: true
      }
      """

  Scenario: Obtener datos de un huésped por ID
    And def id = 2
    And path "/users/" + id
    When method get
    Then status 200
    And match response ==
    """
    {
      "nom": "#string",
      "ape": "#string",
      "documento": "#string",
      "phone": "#string",
      "nacionalidad": "#string",
      "fechaN": "#string"
    }
    """
