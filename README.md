# API-desafio-B2W
Tecnologias utilizadas:

*Java 8
*Spring Boot
*Spring Data MongoDB

Obs: para executar a API foi utilizada aIDE Spring Tool Suit (STS)



Informações Importantes para uso da API:
- Para utilizar a API é necessário configurar o servidor do MongoDB.

Arquivo de configuração application.properties do Spring:
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=b2w

EndPoints da API :
- Listar os planetas Salvos (Localmente) : (GET) http://localhost:8080/planetas 
- Salvar um determinado planteta : (POST) http://localhost:8080/planetas
    Informando um JSON:
     {
        "nome": "Terra",
        "clima": "tropical",
        "terreno": "arido"
    }
  
- Buscar um planeta por id (Localmente): (GET) http://localhost:8080/planetas/{id}
- Busca um planeta po nome (Localmente): (GET) http://localhost:8080/planetas/{nome}/nome
- Remover planeta(Localmente): (DELETE) http://localhost:8080/planetas/{id}


Consumindo a API de terceiro: https://swapi.co/ 
- Consumir a API pelo nome : (Remotamente) http://localhost:8080/swapi/{nome}
Obs: Consulta  pelo nome do planeta do site mencionado acima. Logo em seguida, compara o nome com os planetas que já existem no banco(Localmente). Se achar um nome igual, a variável quantidadeAparicoes é setada para número de quantidade de filmes encontrado na busca da API.

