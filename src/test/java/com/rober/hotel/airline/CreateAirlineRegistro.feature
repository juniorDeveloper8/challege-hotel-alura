Feature: Inserción de datos con autorización de token

Background:
  Given que tengo un token de autorización válido

Scenario: Inserción de datos válidos
  When hago una solicitud POST a "http://localhost:8080/users"
  And la solicitud incluye el siguiente JSON:
    """
    {
      "nom": "alexix",
      "ape": "pipo",
      "phone": "555555",
      "fechaN": "2000-12-11",
      "documento": "78945",
      "nacionalidad": "Ecuatoriano"
    }
    """
  Then la respuesta debe tener un código de estado 201 o 200 OK

Scenario: Inserción de datos inválidos (falta de autorización)
  When hago una solicitud POST a "http://localhost:8080/users" sin un token de autorización
  And la solicitud incluye el mismo JSON de datos que en el escenario anterior
  Then la respuesta debe tener un código de estado 403 no permitido
  And la respuesta debe incluir el mensaje "Token de autorización requerido"
