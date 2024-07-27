@LoginAccount

Feature: Autenticación de Usuario

  Background:
    * configure headers = { 'Content-Type': 'application/json' }
    * def urlBase = 'http://localhost:8080'
    * def urlPath = '/login'

  Scenario: Autenticación exitosa con credenciales válidas
    Given url urlBase + urlPath
    And request { "login": "rober@test.com", "clave": "852rober" }
    When method post
    Then status 200
    And match response == { jwTtoken: '#notnull' }

  Scenario: Fallo de autenticación con credenciales incorrectas
    Given url urlBase + urlPath
    And request { "login": "usuario_incorrecto", "clave": "clave_incorrecta" }
    When method post
    Then status 500
    And karate.log('Response:', response)
    And match response == {"error":"Error interno del servidor"}

  Scenario: Solicitud mal formada (faltan campos)
    Given url urlBase + urlPath
    And request { "login": "rober@test.com" }  # Falta el campo 'clave'
    When method post
    Then status 401
    And karate.log('Response:', response)
    And match response == {"error":"Credenciales inválidas"}

  Scenario: Fallo de autenticación por usuario no encontrado
    Given url urlBase + urlPath
    And request { "login": "usuario_inexistente", "clave": "852rober" }
    When method post
    Then status 500
    And match response == {"error":"Error interno del servidor"}