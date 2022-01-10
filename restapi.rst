========================
REST API Documentation
========================

Recursos
----------

O recurso Recurso representa os recursos educacionais.

==============   ===============
Propriedade       Descrição
==============   ===============
id	         Identificador único de cada recurso
titulo 		 Título do recurso
descricao      	 A descrição do recuro
palavras-chave   Lista de palavras-chave que definem o recurso
link 		 Link de redirecionamento para o recurso
data_criacao     Data de criacao do recurso
data_registro    Data de registro do recurso
==============   ===============

List Resource
~~~~~~~~~~~~~~~

.. code-block:: text

    /recursos

GET
+++++

Retorna uma lista com todos os recursos

.. code-block:: bash

   GET http://localhost:8080/recurso

.. code-block:: js

       
    [
        {
            "id": 1,
            "palavras_chave": 
            [
                "Teste"
            ],
            "titulo": "TESTE",
            "descricao": "Aulas de Java API - 2022",
            "link": "uff.com.br",
            "imagem": "imagem",
            "data_criacao": "2022-01-01",
            "data_registro": "2022-01-10"
        }
    ]


POST 
++++++

Criar um novo recurso.

==============   ===============
Parâmetros       Descrição
==============   ===============
authorId         Identificador do autor do recurso 
==============   ===============

.. code-block:: text

   POST http://localhost:8080/author/{authorId}/recurso
.. code-block:: js

        {
          "id": 0,
          "palavras_chave": [
            "string"
          ],
          "titulo": "string",
          "descricao": "string",
          "link": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_registro": "string"
        }
   

PUT
+++++

Atualiza um recurso sem alterar seu autor.

==============   ===============
Parâmetros        Descrição
==============   ===============
id               Identificador do recurso 
==============   ===============

.. code-block:: text
  
    PUT http://localhost:8080/recurso/{id}

.. code-block:: js

        {
          "id": 0,
          "palavras_chave": [
            "string"
          ],
          "titulo": "string",
          "descricao": "string",
          "link": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_registro": "string"
        }

PUT
+++++

Atualiza um recurso e associar a um novo autor.

==============   ===============
Parâmetros        Descrição
==============   ===============
authorId         Identificador do autor 
recursoId        Identificador do recurso 
==============   ===============

.. code-block:: text
  
    PUT http://localhost:8080/author/{authorId}/recurso/{recursoId}

.. code-block:: js

        {
          "id": 0,
          "palavras_chave": [
            "string"
          ],
          "titulo": "string",
          "descricao": "string",
          "link": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_registro": "string"
        }

DELETE
+++++++

Deletar o recurso  

==============   ===============
Parâmetros        Descrição
==============   ===============
id               Identificador do recurso 
==============   ===============


.. code-block:: text

    DELETE http://localhost:8080/recurso/{id}


.. code-block:: js


Author
-------------

O Author é responsável por criar um recurso educacional

==============   ===============
Propriedade       Descrição
==============   ===============
id	             Identificador único do autor
orcid	         Código identificado de cientistas e outros autores académicos e contribuidores.
email	         Email do autor
nome	         Nome do autor
sobrenome        Sobrenome do autor
afiliacao	     Instituição a qual o autor está vinculado
recursos         Lista de recursos escritos pelo determinado autor
==============   ===============

Endpoints
~~~~~~~~~~~~~~~

.. code-block:: text
   /author
    
    
GET
+++++

Retorna uma lista com todos autores cadastrados

.. code-block:: bash

   GET http://localhost:8080/author

.. code-block:: js

        [
          {
            "id": 1,
            "orcid": "0000-0000-0000-0001",
            "email": "teste@mail.com",
            "nome": "Teste",
            "sobrenome": "Testado",
            "afiliacao": "Universidade Federal do Teste",
            "recursos": [
                          {
                            "id": 1,
                            "palavras_chave": 
                            [
                                "Teste"
                            ],
                            "titulo": "TESTE",
                            "descricao": "Aulas de Teste",
                            "link": "teste.com.br",
                            "imagem": "imagem",
                            "data_criacao": "2022-01-01",
                            "data_registro": "2022-01-10"
                          }
                        ]
              }
        ]
        
GET
++++

Retorna uma lista com todos recursos de um determinado autor

==============   ===============
Param            Description
==============   ===============
id               Identificador do autor
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/author/{id}/recursos
.. code-block:: js

        [
            {
                "id": 1,
                "palavras_chave": 
                [
                    "Teste"
                ],
                "titulo": "TESTE",
                "descricao": "Aulas de Teste",
                "link": "teste.com.br",
                "imagem": "imagem",
                "data_criacao": "2022-01-01",
                "data_registro": "2022-01-10"
            }
        ]
        
GET
++++

Retorna uma lista com todos autores com um determinado sobrenome

==============   ===============
Parâmetro        Descrição
==============   ===============
sobrenome        Sobrenome do autor desejado
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/author/sn

.. code-block:: js

        [
          {
            "id": 0,
            "orcid": "string",
            "email": "string",
            "nome": "string",
            "sobrenome": "string",
            "afiliacao": "string",
            "recursos": [
              {
                "id": 0,
                "palavras_chave": [
                  "string"
                ],
                "titulo": "string",
                "descricao": "string",
                "link": "string",
                "imagem": "string",
                "data_criacao": "string",
                "data_registro": "string"
              }
            ]
          }
        ]

POST 
++++++

Cria um novo autor

.. code-block:: text

   POST http://localhost:8080/author

.. code-block:: js

            {
              "id": 0,
              "orcid": "string",
              "email": "string",
              "nome": "string",
              "sobrenome": "string",
              "afiliacao": "string",
              "recursos": [
                {
                  "id": 0,
                  "palavras_chave": [
                    "string"
                  ],
                  "titulo": "string",
                  "descricao": "string",
                  "link": "string",
                  "imagem": "string",
                  "data_criacao": "string",
                  "data_registro": "string"
                }
              ]
            }



PUT
+++++

Atualiza um autor

==============   ===============
Parâmetros       Descrição
==============   ===============
id               Identificador único do autor
==============   ===============

.. code-block:: text
  
    PUT http://localhost:8080/author/{id}

.. code-block:: js

        {
          "orcid": "string",
          "email": "string",
          "nome": "string",
          "sobrenome": "string",
          "afiliacao": "string",
          "recursos": [
            {
              "id": 0,
              "palavras_chave": [
                "string"
              ],
              "titulo": "string",
              "descricao": "string",
              "link": "string",
              "imagem": "string",
              "data_criacao": "string",
              "data_registro": "string"
            }
          ]
        }

DELETE
+++++++

Deletes a service list and returns the deleted service object

.. code-block:: text

    DELETE /admin/api/v1/service-lists/{service-list} HTTP/1.1

.. code-block:: js

        {
            "name": "Example List",
            "id": "example-list",
            "description": "System is now operational",
            "url": "/api/v1/service-lists/example-list",
        }

Events
-----------

The Events List resource represents all event associated with a given service


==============   ===============
Property         Description
==============   ===============
sid	         The unique identifier by which to identify the event
message	         The message associated with this event
timestamp	 The time at which this event occurred, given in RFC 1132 format.
url	         The URL of the specific event resource
status	         The status of this event, as described by the Statuses resource
==============   ===============


List Resource
~~~~~~~~~~~~~~~~~~~~

.. code-block:: text

    /admin/api/v1/services/{service}/events

GET
++++

Returns all events associated with a given service in reverse chronological order.

.. code-block:: text

    GET /admin/api/v1/services/{service}/events HTTP/1.1

.. code-block:: js

        {
            "events": [
                {
                    "timestamp": "Mon, 28 Jun 2010 22:17:06 GMT",
                    "message": "Problem fixed", 
                    "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GBAM",
                    "url": "/api/v1/services/example-service/events/ahJpc215d2Vic2VydmljZWRvd2",
                    "status": {
                        "id": "down",
                        "name": "Down",
                        "description": "An explanation of what this status represents",
                        "level": "ERROR",
                        "image": "/images/status/cross-circle.png",
                        "url": "/api/v1/statuses/down",
                    },
                }, 
                {
                    "timestamp": "Mon, 28 Jun 2010 22:18:06 GMT",
                    "message": "Might be up", 
                    "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
                    "url": "/api/v1/services/example-service/events/ahJpc215d2Vic..."
                    "status": {
                        "id": "down",
                        "name": "Down",
                        "description": "An explanation of what this status represents",
                        "level": "ERROR",
                        "image": "/images/status/cross-circle.png",
                        "url": "/api/v1/statuses/down",
                    },
                }
            ]
        }

The Events List resource also supports filtering events via dates. To filter events, place on of the following options into the query string for a GET request

While the format of these parameters is very flexible, we suggested either the RFC 2822 or RFC 1123 format due to their support for encoding timezone information.

Events List URL Filtering Options

======= ============
Option	Description
======= ============
start	Only show events which started after this date, inclusive.
end     Only show events which started before date, inclusive.
======= ============

.. code-block:: text

    GET /admin/api/v1/services/{service}/events?start=2010-06-10 HTTP/1.1

would return all events starting after June 6, 2010.

Similarly, both "start" and "end" can be used to create date ranges

.. code-block:: text

    GET /admin/api/v1/services/{service}/events?end=2010-06-17&start=2010-06-01 HTTP/1.1

would return all events between June 6, 2010 and June 17, 2010



POST
+++++

Creates a new event for the given service and returns the newly created event object. All arguments are required.

========  ==============
Param	  Description
========  ==============
status	  The system status for the event. This must be a valid system status identifier found in the Statuses List resource
message	  The message for the event
========  ==============

.. code-block:: text

    POST /admin/api/v1/services/{service}/events HTTP/1.1 status=AVAILABLE&message=System%20is%20now%20operational

.. code-block:: js

        {
            "timestamp": "Mon, 28 Jun 2010 22:18:06 GMT"
            "message": "Might be up", 
            "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "url": "/api/v1/services/example-service/events/ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "status": {
                "id": "down",
                "name": "Down",
                "description": "An explanation of what this status represents",
                "level": "ERROR",
                "image": "/images/status/cross-circle.png",
                "url": "/api/v1/statuses/down",
            },
        }

Current Event
~~~~~~~~~~~~~~~~~

The Current Service Event resource simply returns the current event for a given service.

.. code-block:: text

    /admin/api/v1/services/{service}/events/current

GET
++++

Returns the current event for a given service.

.. code-block:: text

    GET /admin/api/v1/services/{service}/events/current HTTP/1.1

.. code-block:: js

        {
            "timestamp": "Mon, 28 Jun 2010 22:17:06 GMT",
            "message": "Might be up", 
            "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "url": "/api/v1/services/example-service/events/ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "status": {
                "id": "down",
                "name": "Down",
                "description": "An explanation of what this status represents",
                "level": "ERROR",
                "image": "/images/status/cross-circle.png",
                "url": "/api/v1/statuses/down",
            },
        }

Instance Resource
~~~~~~~~~~~~~~~~~~~~

The Event Instance resource represents an individual event for a given service.

.. code-block:: text
 
    /admin/api/v1/services/{service}/events/{sid}

GET
++++

Returns a service event with the given event sid. The event's status object is also returned as well.

.. code-block:: text

    GET /admin/api/v1/services/{service}/events/{sid} HTTP/1.1

.. code-block:: js

        {
            "timestamp": "Mon, 28 Jun 2010 22:17:06 GMT",
            "message": "Might be up", 
            "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "url": "/api/v1/services/example-service/events/ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "status": {
                "id": "down",
                "name": "Down",
                "description": "An explanation of what this status represents",
                "level": "ERROR",
                "image": "/images/status/cross-circle.png",
                "url": "/api/v1/statuses/down",
            }
        }


DELETE
++++++++

Deletes the given event and returns the deleted event

.. code-block:: text

    DELETE /admin/api/v1/services/{service}/events/{sid} HTTP/1.1

.. code-block:: js

        {
            "timestamp": "Mon, 28 Jun 2010 22:17:06 GMT",
            "message": "Might be up", 
            "sid": "ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "url": "/api/v1/services/example-service/events/ahJpc215d2Vic2VydmljZWRvd25yCwsSBUV2ZW50GA8M",
            "status": {
                "id": "down",
                "name": "Down",
                "description": "An explanation of what this status represents",
                "level": "ERROR",
                "image": "/images/status/cross-circle.png",
                "url": "/statuses/down",
            },    
        }

Statuses
-----------
The Status resource represents a possible status for a service.

==============   ===============
Property         Description
==============   ===============
id	         The unique identifier by which to identify the status
name	         The name of the status, defined by the user
description	 The description of the status
url	         The URL of the specific status resource
level	         The level of this status. Can be any value listed in the Levels List resource
image	         The URL of the image for this status
==============   ===============

List Resource
~~~~~~~~~~~~~~~~

.. code-block:: text

    /admin/api/v1/statuses


The Status List resource represents all possible systems statuses.


GET
+++++

Returns all service statuses

.. code-block:: text

    GET /admin/api/v1/statuses HTTP/1.1

.. code-block:: js

        {
            "statuses": [
                {
                    "name": "Available",
                    "id": "available",
                    "description": "An explanation of what this status represents",
                    "level": "NORMAL",
                    "image": "/images/status/tick-circle.png",
                    "url": "api/v1/statuses/up",
                },
                {
                    "name": "Down",
                    "id": "down",
                    "description": "An explanation of what this status represents",
                    "level": "ERROR",
                    "image": "/images/status/cross-circle.png",
                    "url": "api/v1/statuses/down",
                },
            ]
        }

POST
++++++

Creates a new status and returns this newly created status. All parameters are required.

============  ==============
Param	      Description
============  ==============
name	      The name of the status
description   The description of the status
level	      The level of the status. lues listed in the rce
image	      The filename of the image, with no extension. See the status-images resource
============  ==============

.. code-block:: text

    POST /admin/api/v1/statuses HTTP/1.1 name=Down&description=A%20new%20status&severity=1000&image=cross-circle.png

.. code-block:: js

        {
            "name": "Down",
            "id": "down"
            "description": "A new status",
            "level": "ERROR",
            "image": "cross-circle",
            "url": "/api/v1/statuses/down",
        }

Instance Resource
~~~~~~~~~~~~~~~~~~~~~

The Status Instance resource represents a single service status

.. code-block:: text

    /admin/api/v1/statuses/{name}


GET
+++++

Returns a status object

.. code-block:: text

   GET /admin/api/v1/services HTTP/1.1

.. code-block:: js

        {
            "name": "Down",
            "id": "down",
            "description": "A new status",
            "level": "ERROR",
            "image": "/images/status/cross-circle.png",
            "url": "/api/v1/statuses/down",
        }

POST
++++++

Update the given status. All the following parameters are optional.

============  ==============
Param	      Description
============  ==============
name	      The name of the status
description   The description of the status
level	      The level of the status. lues listed in the rce
image	      The filename of the image, with no extension. See the status-images resource
============  ==============

.. code-block:: text

    POST /admin/api/v1/statuses HTTP/1.1 description=A%20new%20status&severity=1010&image=cross-circle.png

.. code-block:: js

        {
            "name": "Down",
            "id": "down",
            "description": "A new status",
            "level": "ERROR",
            "image": "/images/status/cross-circle.png",
            "url": "/api/v1/statuses/down",
        }

DELETE
+++++++++

Delete the given status and return the deleted status


.. code-block:: text

    DELETE /admin/api/v1/statuses/{name}

.. code-block:: js

        {
            "name": "Down",
            "id": "down",
            "description": "A new status",
            "level": "ERROR",
            "image": "/images/status/cross-circle.png",
            "url": "/api/v1/statuses/down",
        }

Status Levels
----------------
The Status Levels resource is a read-only resource which lists the possible levels for a status.

List Resource
~~~~~~~~~~~~~~~~~

.. code-block:: text

    /admin/api/v1/levels

GET
+++++
Returns a list of possible status levels in increasing severity

.. code-block:: text

    GET /admin/api/v1/levels

.. code-block:: js

        {
            "levels": [
                "NORMAL", 
                "WARNING", 
                "ERROR", 
                "CRITICAL",
            ]
        }


Status Images
----------------
The Status Images resource is a read-only resource which lists the icons available to use for statuses

List Resource
~~~~~~~~~~~~~~~

.. code-block:: text

    /admin/api/v1/status-images

GET
++++++

Returns a list of status images.

.. code-block:: text

    GET /admin/api/v1/status-images

.. code-block:: js

        {
            "images": [
                {
                    "name": "sample-image",
                    "url": "/status-images/sample-image.png",
                },
                {
                    "name": "sample-image",
                    "url": "/status-images/sample-image.png",
                },
            ]
        }
