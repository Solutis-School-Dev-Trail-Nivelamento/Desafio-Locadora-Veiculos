# Desafio Locadora de Veículos

## Swagger para requisição http:
1. Inicie a aplicação.
2. **Swagger:** `http://localhost:8080/swagger-ui/index.html`
  
## Estrutura do projeto:
- **Entidade:** Representa a tabela no banco.
- **Repositório:** Gerencia a persistência e recuperação dos dados.
- **Serviço:** Contém a lógica de negócio.
- **Controlador:** Expõe a API para operações HTTP.

## DTO
A camada DTO (Data Transfer Object) também é comumente utilizada em aplicações Spring Boot, especialmente em projetos mais complexos. Ela serve como uma forma de separar a representação dos dados que trafegam entre as camadas da aplicação (especialmente entre a camada de serviço e o controlador) da lógica interna das entidades do domínio.

### Benefícios de Usar DTOs
- **Encapsulamento:** Protege a entidade de mudanças desnecessárias na API.
- **Validação:** Permite aplicar validações específicas ao transferir dados.
- **Segurança:** Evita que campos sensíveis das entidades sejam expostos.
- **Desempenho:** Pode reduzir a quantidade de dados transferidos, especialmente ao filtrar campos não necessários.