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

Deletar um autor  

==============   ===============
Parâmetros        Descrição
==============   ===============
id               Identificador do author 
==============   ===============

.. code-block:: text

    DELETE /author/{id}

.. code-block:: js


Eventos
-----------

Os eventos são tipos de coleção representam uma coleção de eventos de recursos educacionais


==============   ===============
Propriedade         Descrição
==============   ===============
id	             Identificador único do evento
recursos	     Lista de recursos representações pelos eventos
titulo	         Título da coleção de eventos
descricao	     Descrição da coleção de eventos
imagem	         Imagem representativa
data_criacao	 Data da criação da coleção de eventos
data_fim	     Data de fim da coleção de eventos
==============   ===============


Resource
~~~~~~~~~~~~~~~~~~~~

.. code-block:: text

    /eventos

GET
++++

Retorna uma lista com todos eventos

.. code-block:: text

    GET /eventos

.. code-block:: js

        [
          {
            "id": 1,
            "recursos": [],
            "titulo": "Spring",
            "descricao": "Evento exemplo",
            "imagem": "imagem",
            "data_criacao": "2022-01-01",
            "data_fim": "2022-01-10"
          }
        ]

GET
++++

Retorna uma lista com todos recursos de um determinado evento

==============   ===============
Param            Description
==============   ===============
id               Identificador do evento
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/evento/{id}/recursos
    
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

Retorna uma lista com todos eventos dentro de um dado período de tempo

==============   ===============
Param            Description
==============   ===============
data_criacao     Data mínima do período
data_fim         Data máxima do período
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/evento/{data_criacao}/{data_fim}
    
.. code-block:: js

        [
          {
            "id": 0,
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
            ],
            "titulo": "string",
            "descricao": "string",
            "imagem": "string",
            "data_criacao": "string",
            "data_fim": "string"
          }
        ]

POST
+++++

Cria um novo evento


.. code-block:: text

    POST /evento

.. code-block:: js

        {
          "id": 0,
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
          ],
          "titulo": "string",
          "descricao": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_fim": "string"
        }

POST
++++

Cria um evento associando a um recurso existente 

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
==============   ===============

.. code-block:: text

    POST /evento/{recursoId}

.. code-block:: js

        {
          "id": 0,
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
          ],
          "titulo": "string",
          "descricao": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_fim": "string"
        }

PUT
++++

Atualiza um evento sem alterar seu recurso

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único do evento
==============   ===============

.. code-block:: text

    PUT /evento/{id}

.. code-block:: js

        {
          "id": 0,
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
          ],
          "titulo": "string",
          "descricao": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_fim": "string"
        }

PUT
++++

Atualiza um evento associando a um recurso existente

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
eventoId         Identificador único do evento
==============   ===============

.. code-block:: text

    PUT /recurso/{recursoId}/evento/{eventoId}/

.. code-block:: js

        {
          "id": 0,
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
          ],
          "titulo": "string",
          "descricao": "string",
          "imagem": "string",
          "data_criacao": "string",
          "data_fim": "string"
        }


DELETE
++++++++

Deleta o evento

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único do evento
==============   ===============

.. code-block:: text

    DELETE /evento/{id}

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
