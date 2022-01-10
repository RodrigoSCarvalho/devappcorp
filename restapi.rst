========================
REST API Documentation
========================

Recursos
----------

O resource Recurso representa os recursos educacionais.

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

Resource
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

Resource
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

    DELETE http://localhost:8080/author/{id}

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

    http://localhost:8080/eventos

GET
++++

Retorna uma lista com todos eventos

.. code-block:: text

    GET http://localhost:8080/eventos

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

    POST http://localhost:8080/evento

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

    POST http://localhost:8080/evento/{recursoId}

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

    PUT http://localhost:8080/evento/{id}

.. code-block:: js

        {
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

    PUT http://localhost:8080/recurso/{recursoId}/evento/{eventoId}/

.. code-block:: js

        {
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

    DELETE http://localhost:8080/evento/{id}

cursos
-----------

Os cursos são tipos de coleção representam uma coleção de cursos de recursos educacionais


==============   ===============
Propriedade         Descrição
==============   ===============
id	             Identificador único do curso
recursos	     Lista de recursos representações pelos cursos
titulo	         Título da coleção de cursos
descricao	     Descrição da coleção de cursos
imagem	         Imagem representativa
data_registro	 Data da registro da coleção de cursos
==============   ===============


Resource
~~~~~~~~~~~~~~~~~~~~

.. code-block:: text

    http://localhost:8080/curso

GET
++++

Retorna uma lista com todos cursos

.. code-block:: text

    GET http://localhost:8080/curso

.. code-block:: js

        [
          {
            "id": 1,
            "recursos": [],
            "titulo": "Spring",
            "descricao": "Curso exemplo",
            "imagem": "imagem",
            "data_registro": "2022-01-01",
          }
        ]

GET
++++

Retorna uma lista com todos recursos de um determinado curso

==============   ===============
Param            Description
==============   ===============
id               Identificador do curso
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/curso/{id}/recursos
    
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
                "data_registro": "2022-01-01",
            }
        ]
        

POST
+++++

Cria um novo curso


.. code-block:: text

    POST http://localhost:8080/curso

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
          "data_registro": "string",
        }

POST
++++

Cria um curso associando a um recurso existente 

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
==============   ===============

.. code-block:: text

    POST http://localhost:8080/recurso/{recursoId}/curso

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
          "data_registro": "string",
        }

PUT
++++

Atualiza um curso sem alterar seu recurso

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único do curso
==============   ===============

.. code-block:: text

    PUT http://localhost:8080/curso/{id}

.. code-block:: js

        {
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
          "data_registro": "string",
        }

PUT
++++

Atualiza um curso associando a um recurso existente

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
cursoId          Identificador único do curso
==============   ===============

.. code-block:: text

    PUT http://localhost:8080/recurso/{recursoId}/curso/{cursoId}/

.. code-block:: js

        {
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
          "data_registro": "string",
        }


DELETE
++++++++

Deleta o curso

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único do curso
==============   ===============

.. code-block:: text

    DELETE http://localhost:8080/curso/{id}

Coleção
-----------

As colecões representam uma coleção de recursos educacionais


==============   ===============
Propriedade         Descrição
==============   ===============
id	             Identificador único da colecao
recursos	     Lista de recursos representações pelas coleções
titulo	         Título da coleção 
descricao	     Descrição da coleção
imagem	         Imagem representativa
==============   ===============


Resource
~~~~~~~~~~~~~~~~~~~~

.. code-block:: text

    http://localhost:8080/colecao

GET
++++

Retorna uma lista com todas coleções

.. code-block:: text

    GET http://localhost:8080/colecao

.. code-block:: js

        [
          {
            "id": 1,
            "recursos": [],
            "titulo": "Spring",
            "descricao": "colecao exemplo",
            "imagem": "imagem",
          }
        ]

GET
++++

Retorna uma lista com todos recursos de uma determinada colecao

==============   ===============
Parâmetro         Descrição
==============   ===============
id               Identificador da coleção
==============   ===============


.. code-block:: bash

    GET http://localhost:8080/colecao/{id}/recursos
    
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
        

POST
+++++

Cria um novo colecao


.. code-block:: text

    POST http://localhost:8080/colecao

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
        }

POST
++++

Cria um coleção associando a um recurso existente 

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
==============   ===============

.. code-block:: text

    POST http://localhost:8080/colecao/{recursoId}

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
        }

PUT
++++

Atualiza um colecao sem alterar seu recurso

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único da coleção
==============   ===============

.. code-block:: text

    PUT http://localhost:8080/colecao/{id}

.. code-block:: js

        {
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
        }

PUT
++++

Atualiza um colecao associando a um recurso existente

==============   ===============
Parâmetro            Descrição
==============   ===============
recursoId        Identificador único do recurso
colecaoId         Identificador único da coleção
==============   ===============

.. code-block:: text

    PUT http://localhost:8080/recurso/{recursoId}/colecao/{colecaoId}/

.. code-block:: js

        {
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
        }


DELETE
++++++++

Deleta a coleção

==============   ===============
Parâmetro            Descrição
==============   ===============
id               Identificador único da coleção
==============   ===============

.. code-block:: text

    DELETE http://localhost:8080/colecao/{id}
