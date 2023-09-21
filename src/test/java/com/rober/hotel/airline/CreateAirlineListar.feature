Feature: Listar datos con autorización de token

Background:
  Given que tengo un token de autorización válido

Scenario: Listar datos válidos
  When hago una solicitud GET a "http://localhost:8080/users"
  Then la respuesta debe tener un código de estado 200 OK
  And la respuesta debe incluir al menos un elemento de datos

Scenario: Listar datos inválidos (falta de autorización)
  When hago una solicitud GET a "http://localhost:8080/users" sin un token de autorización
  Then la respuesta debe tener un código de estado 403 no autorizado
  And la respuesta debe incluir el mensaje "Token de autorización requerido"
