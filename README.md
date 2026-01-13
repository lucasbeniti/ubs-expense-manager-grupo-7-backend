# 💼 UBS Expense Manager — Backend

API REST corporativa para **controle de gastos de funcionários**, desenvolvida para atender às necessidades do UBS, garantindo **rastreabilidade**, **governança**, **segurança** e **controle de orçamento** sobre despesas de viagens, refeições e custos operacionais.

Este repositório contém **exclusivamente o backend** da aplicação.

---

## 📌 Visão Geral

O sistema permite que funcionários registrem despesas corporativas, que passam por um **fluxo de aprovação**, com **regras de limite por categoria**, **alertas automáticos** e **controle de acesso por perfil**.

A aplicação foi projetada seguindo **Clean Architecture**, com foco em **baixo acoplamento**, **alta testabilidade** e **clareza de responsabilidades**.

---

## 🧠 Contexto de Negócio

Atualmente, o UBS realiza o controle de despesas via planilhas e e-mails, o que gera:

- Falta de rastreabilidade
- Risco de reembolsos indevidos
- Estouro de orçamento
- Dificuldade em auditorias

Este backend resolve esses problemas através de:

- Registro estruturado de despesas
- Workflow de aprovação formal
- Regras automatizadas de limite
- Perfis de acesso bem definidos
- Eventos de domínio para alertas e validações

---

## 🛠️ Tecnologias Utilizadas

### Core
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA + Hibernate**

### Banco de Dados
- **PostgreSQL**
- **Flyway** para versionamento de schema

### Segurança
- **Spring Security**
- Autenticação com **usuário e senha (mock)**
- Autorização baseada em **Roles**

### Documentação
- **Swagger / OpenAPI**

### Testes
- **JUnit 5**
- **Mockito**
- **Testcontainers**

---

## 🧱 Arquitetura

### Clean Architecture

O projeto segue os princípios da **Clean Architecture**, garantindo:

- Independência de frameworks
- Domínio isolado de detalhes externos
- Facilidade de testes
- Evolução segura do código

### Camadas Principais

---

## 🗂️ Estrutura de Pastas

```text
src/main/java/com/ubs/expensecontrol
│
├── Controller
|
├── Dto
│
├── Entity
│
├── Exception
│
├── Mapper
│
├── Repository
│
├── Service

```

## ▶️ Execução Local

### Pré-requisitos

Antes de executar a aplicação, certifique-se de que você possui:

- **Docker Desktop instalado e em execução**
- **Java 21**
- Permissão para executar o Gradle Wrapper

```bash
# Executar a aplicação Spring Boot juntamente com o container Docker
./gradlew clean build -x test
docker compose up --build

