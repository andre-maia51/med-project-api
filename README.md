# Med-Project

**Med-Project** é uma API REST de um sistema médico criada para fins de estudo. Ela permite realizar operações CRUD para médicos e pacientes, além de funcionalidades de agendamento e cancelamento de consultas.

## Tecnologias Utilizadas

- **Java** (JDK 22)
- **Spring Boot** - Para a criação da API REST
- **PostgreSQL** - Banco de dados relacional
- **Spring Security com JWT** - Para autenticação
- **Flyway** - Para gerenciamento de migrations
- **Lombok** - Para reduzir o código boilerplate
- **JPA** - Para mapeamento objeto-relacional (ORM)
- **Spring Doc** - Para gerar a documentação da API

## Instalação

Para rodar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:
    ```bash
    git clone https://github.com/andre-maia51/med-project-api.git
    ```

2. Certifique-se de ter o **PostgreSQL** instalado e crie um banco de dados:
    ```sql
    CREATE DATABASE med-project;
    ```

3. Configure o arquivo `application.properties` com suas credenciais do PostgreSQL ou utilize de variáveis de ambiente:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/med-project
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

4. Rode as migrations do Flyway ao iniciar a aplicação.

5. Para gerar e rodar o projeto, execute:
    ```bash
    ./mvnw clean install
    java -jar target/med-project.jar
    ```

## Como Usar

Você pode testar as requisições da API utilizando o **Insomnia** ou outra ferramenta para testar APIs.

### Documentação da API

A documentação da API é gerada automaticamente pelo **Spring Doc**. Para acessá-la, siga os passos:

1. Inicie a aplicação.
2. Abra o navegador e vá para: `http://localhost:8080/swagger-ui.html`.
3. A documentação interativa estará disponível para realizar testes e consultas.

## Contato

Se tiver alguma dúvida ou sugestão, entre em contato pelo Telegram: [@andre_m51](https://t.me/andre_m51)

---

*Este projeto foi criado para fins de estudo e não possui licenciamento nem está aberto para contribuições no momento.*
