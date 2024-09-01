# Desafio Locadora de Veículos

## Descrição
Esta aplicação é um sistema de gerenciamento de locação de veículos, construído com Java e Spring Boot.

## Requisitos
Java 17: A aplicação requer o Java 17.

Gradle: Certifique-se de que o Gradle está instalado na sua máquina. 

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

## Dependências: 

O projeto utiliza as seguintes dependências:

- Spring Boot Starter Data JPA: Para a integração com o JPA.
- Spring Boot Starter Web: Para construir a API REST.
- Springdoc OpenAPI: Para gerar a documentação OpenAPI.
- MapStruct: Para mapeamento de objetos.
- Lombok: Para reduzir o boilerplate code.
- Spring Boot Starter Actuator: Para monitoramento da aplicação.
- MySQL Connector/J: Para conectar ao banco de dados MySQL.
- JUnit: Para testes.
- Logback: Para logging.

## Configuração
Banco de Dados
Configuração Inicial: No arquivo src/main/resources/application.yml, configure o banco de dados MySQL com a URL, o usuário e a senha apropriados.

## Criação das Tabelas: 
Para realizar a criação da primeira tabela, altere o campo ddl-auto para "create". Depois de criar as tabelas iniciais, altere de volta para "update" para manter o esquema atualizado.

Ordem para adicionar dados às tabelas:
Use o Swagger UI para adicionar dados às tabelas na seguinte ordem:

- Acessório
- Fabricante
- Modelo
- Carro
- Cliente
- Aluguel
- Carrinho
- Pagamento


