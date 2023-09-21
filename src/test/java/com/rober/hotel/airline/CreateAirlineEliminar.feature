Feature: Desactivación de usuario con autorización de token

Background:
  Given que tengo un token de autorización válido

Scenario: Desactivación de usuario válido
  When hago una solicitud PUT a "http://localhost:8080/users/1"
  Then la respuesta debe tener un código de estado 200 OK
  And la respuesta debe incluir el mensaje "Usuario desactivado correctamente"

Scenario: Desactivación de usuario inválida (falta de autorización)
  When hago una solicitud PUT a "http://localhost:8080/users/1" sin un token de autorización
  Then la respuesta debe tener un código de estado 403 no autorizado
  And la respuesta debe incluir el mensaje "Token de autorización requerido"
