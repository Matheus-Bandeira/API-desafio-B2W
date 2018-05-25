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


