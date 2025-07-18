
---

# API de Controle de Veículos e Viagens

API REST desenvolvida com **Spring Boot** e **Java 17**, para controle de veículos no pátio, registro de viagens e histórico de movimentações.

---

## Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Gradle -> escolhi esse por ja conhecer, base do flutter
* Lombok

---

## Criação e Execução

Projeto gerado via [Spring Initializr](https://start.spring.io/).

Para rodar:

```bash
.\gradlew.bat bootRun
```

---

## Conexão com Banco (PostgreSQL)

Configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=usuario
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Estrutura

* `model/`: Entidades
* `repository/`: Repositórios JPA
* `controller/`: Endpoints REST
* `dto/`: Transferência de dados
* `enums/`: Enumerações (ex: StatusVeiculo)
* `service/`: Funções e regras de negocios (onde trabalhei algumas coisas)


---

## Endpoints
 Testes feito no postman - pasta portaria-backend\postman\SPRING-PORTARIA.postman_collection.json
### Veículos

* `GET /veiculos` — Lista todos
* `GET /veiculos?status=EM_VIAGEM` — Fora do pátio
* `GET /veiculos?status=NO_PATIO` — No pátio
* `POST /veiculos` — Cadastrar

### Funcionários

* `GET /funcionarios`
* `POST /funcionarios`

### Viagens

* `POST /viagens/saida` — Saída
* `POST /viagens/retorno` — Retorno
* `GET /registros` — Histórico

---

## Observações

* `@ManyToOne` faz o mapeamento automático das chaves estrangeiras.
* Consultas personalizadas feitas com `@Query`.
* `JpaRepository` fornece os métodos CRUD prontos.

---

## Dificuldades

* Estou acostumado a criar o MER e a modelagem diretamente no SQL. Já utilizei o Quarkus em um projeto de API durante a faculdade. Trabalhar com uma framework como o Spring Boot foi uma experiência muito boa — ela automatiza e facilita bastante o processo.
* Uma descoberta interessante foi que a anotação `@ManyToOne` faz o gerenciamento automático das chaves estrangeiras, criando as colunas relacionadas no banco de dados sem precisar declarar manualmente.

---

## Desafios

* Meu maior desafio foi lidar com uma framework nova, mas isso é questão de prática — a gente aprende sem erro. Conversei com um amigo que trabalha com Java e Kotlin (Android nativo), e ele me deu boas orientações.
* Me senti confortável com o teste, de certa forma. Já desenvolvi várias APIs REST antes, mesmo usando outra linguagem. Como a base conceitual eu já tinha, só precisei me adaptar à sintaxe e estrutura do Java com Spring Boot.

---
