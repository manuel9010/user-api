El proyecto se desarrollo utlizando la tecnologia de spring boot ,  e implmentando los modulos de spring web  y security con jwt como base , para la persitencia se utilizo jpa con hibernate.

La arquitectura del proyecto con la cual se organizo fue por capas:

-Controller : representa la capa rest
-Service : representa la capa de la logica de negocio
-Repository : representa la capa de persistencia
-Model: representa la capa de dtos y entidades

Los endpoits para probar son los siguientes:

POST /create
Example Body:

{
"name": "Manuel Cantillo",
"email": "manuel@cantillo.com",
"password": "1234", //expresion regular solo numeros minimo 4 digitos maximo 7
"phones": [
{
"number": "767657",
"citycode": "1",
"contrycode": "57"
}
]
}

GET /user/1
Header Example:
Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW51ZWxAY2FudGlsbG8uY29tIiwiZXhwIjoxNjM5Mzk2MTA4LCJpYXQiOjE2MzkzNzgxMDh9.tKxmISdkB4zBi9j8Owh58g5UWuv8rsP3-ELa-g38bl6At-Gm3wUZi4LfPDfoK7uzunfol41TLmlz9O0d3lx3Vg

PUT /user/1
Example Request:
Header Example:
Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW51ZWxAY2FudGlsbG8uY29tIiwiZXhwIjoxNjM5Mzk2MTA4LCJpYXQiOjE2MzkzNzgxMDh9.tKxmISdkB4zBi9j8Owh58g5UWuv8rsP3-ELa-g38bl6At-Gm3wUZi4LfPDfoK7uzunfol41TLmlz9O0d3lx3Vg

Example Body:

{
"name": "Manuel Cantillo",
"email": "manuel@gmail.com",
"password": "77777",
"phones": [
{
"number": "2222",
"citycode": "2",
"contrycode": "56"
}
]
}

DELETE /user/1
Header Example:
Authorization Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW51ZWxAY2FudGlsbG8uY29tIiwiZXhwIjoxNjM5Mzk2MTA4LCJpYXQiOjE2MzkzNzgxMDh9.tKxmISdkB4zBi9j8Owh58g5UWuv8rsP3-ELa-g38bl6At-Gm3wUZi4LfPDfoK7uzunfol41TLmlz9O0d3lx3Vg

