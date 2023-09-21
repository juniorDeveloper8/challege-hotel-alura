Feature: Create an airline

Background:
  Given url 'http://localhost:8080/login'
  And request {"login":"prueba@test.com","clave":"roberth787"}
  When method post
  Then status 200
  And def authToken = response.authToken

Scenario: Create an airline with valid data
  Given url 'http://localhost:8080/login'
  And header Authorization = 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcnVlYmFAdGVzdC5jb20iLCJpc3MiOiJ2b2xsIG1lZCIsImlkIjoxLCJleHAiOjE2OTUyNjkzNDd9.wSbvyzRrm4q43v9KnLcwegdsho8B3uauM5O3JpFjm3M'
  # Agrega el token como encabezado de autorización
  And request {"login":"prueba@test.com","clave":"roberth787"}
  When method post
  Then status 200