Feature: Actualización de datos con autorización de token

Background:
  Given que tengo un token de autorización válido

Scenario: Actualización de datos válida
  When hago una solicitud PUT a "http://localhost:8080/users/1" con el siguiente JSON:
    """
    {
      "nom": "NuevoNombre",
      "ape": "NuevoApellido",
      "fechaN": "2001-01-01"
    }
    """
  Then la respuesta debe tener un código de estado 200 OK
  And la respuesta debe incluir el mensaje "Datos actualizados correctamente"

Scenario: Actualización de datos inválida (falta de autorización)
  When hago una solicitud PUT a "http://localhost:8080/users/1" sin un token de autorización con el siguiente JSON:
    """
    {
      "nom": "NuevoNombre",
      "ape": "NuevoApellido",
      "fechaN": "2001-01-01"
    }
    """
  Then la respuesta debe tener un código de estado 403 no autorizado
  And la respuesta debe incluir el mensaje "Token de autorización requerido"

Scenario: Actualización de datos inválida (campos no permitidos)
  When hago una solicitud PUT a "http://localhost:8080/users/1" con el siguiente JSON:
    """
    {
      "phone": "555555"
    }
    """
  Then la respuesta debe tener un código de estado 400 Bad Request
  And la respuesta debe incluir el mensaje "Actualización no permitida para el campo 'phone'"
