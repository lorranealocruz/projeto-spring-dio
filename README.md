# API de Clientes com Integração ViaCEP

Este projeto é uma API REST desenvolvida com **Java e Spring Boot** que demonstra a aplicação de **padrões de projeto** e integração com uma **API externa (ViaCEP)** para consulta automática de endereços a partir do CEP.

O projeto foi desenvolvido como prática de **Spring Boot, Spring Data JPA, OpenFeign e Swagger/OpenAPI**.

---

# Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Cloud OpenFeign
- H2 Database
- Swagger / OpenAPI
- Maven

---

# Arquitetura do Projeto

O projeto segue uma estrutura em camadas:

### Camadas

**Controller**
- Responsável por expor os endpoints da API.

**Service**
- Contém as regras de negócio.

**Repository**
- Interface de acesso ao banco de dados utilizando Spring Data JPA.

**Model**
- Entidades que representam as tabelas do banco de dados.

---

# Integração com ViaCEP

A API utiliza **OpenFeign** para consumir o serviço externo do ViaCEP.

Fluxo:

1. O cliente envia um CEP ao cadastrar um cliente.
2. O sistema verifica se o endereço já existe no banco.
3. Caso não exista, a API consulta o **ViaCEP**.
4. O endereço retornado é salvo no banco.
5. O cliente é associado ao endereço.

---

# Banco de Dados

O projeto utiliza **H2 Database (in-memory)** para facilitar testes e execução local.


---

# Documentação da API (Swagger)

A documentação da API pode ser acessada após iniciar a aplicação:
```bash
http://localhost:8080/swagger-ui/index.html
```

## 👩‍💻 Autora

Projeto desenvolvido por **Lorrane Aló Cruz**, como parte do aprendizado em Java e Spring Boot.

