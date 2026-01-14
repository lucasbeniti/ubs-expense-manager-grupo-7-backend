# 💼 UBS ExpenseManager - Backend

O sistema permite que funcionários registrem despesas corporativas, que passam por um **fluxo de aprovação**, com **regras de limite por categoria**, **alertas automáticos** e **controle de acesso por perfil**.

A aplicação foi projetada seguindo **Clean Architecture**, com foco em **baixo acoplamento**, **alta testabilidade** e **clareza de responsabilidades**.

---

## 📋 Sobre o Projeto

Backend da solução **UBS ExpenseManager**, desenvolvido para substituir o controle manual de despesas corporativas que hoje é feito via planilhas e e-mails.

### Problema Resolvido

| Antes | Depois |
| --- | --- |
| ❌ Processos manuais sem rastreabilidade | ✅ Workflow automatizado com auditoria completa |
| ❌ Risco de reembolsos indevidos | ✅ Validação automática contra políticas |
| ❌ Estouro de orçamento | ✅ Alertas preventivos e bloqueios por limite |
| ❌ Baixa eficiência operacional | ✅ Aprovação em tempo real com notificações |

### Fluxo de Aprovação

```jsx
Funcionário         Gestor Direto       Financeiro
    │                    │                   │
    ├─── Cria ──────────>│                   │
    │    Despesa         │                   │
    │                    │                   │
    │                    ├─── Aprova ──────> │
    │                    │                   │
    │                    │                   ├─── Valida Final
    │ <──────────────────┴─────────────────-─┘
         Status Atualizado
```

---

## ✨ Funcionalidades

### 🔐 Autenticação e Autorização

- Autenticação segura com usuário e senha (hash BCrypt)
- Controle de acesso baseado em roles:
    - **Employee**: Criar despesas próprias e consultar histórico pessoal
    - **Manager**: Aprovar/rejeitar despesas da equipe direta
    - **Finance**: Aprovação final, gerenciar alertas e relatórios consolidados
- Validação de permissões em cada endpoint via `@PreAuthorize`

---

### 🧾 Gestão de Despesas

- Registro de despesas com categorização (Viagem, Refeição, Transporte, Outros)
- Validação automática contra limites configurados
- CRUD completo com filtros (status, categoria, período, funcionário)
- Armazenamento de metadados de comprovantes
- Histórico completo de alterações com timestamps e autoria

---

### ✅ Workflow de Aprovação

- Fluxo hierárquico via State Pattern: `PENDENTE` → `APROVADA_GESTOR` → `APROVADA_FINANCEIRO`
- Validação de transições (apenas gestor direto ou Finance podem aprovar)
- Endpoints de aprovação/rejeição com motivo obrigatório
- Rastreabilidade completa: quem aprovou/rejeitou e quando

---

### 🚨 Alertas e Validações

- Geração automática de alertas via Domain Events
- Validação de limites por categoria (diário/mensal configuráveis)
- Controle de orçamento por departamento
- Tipos de alertas: `VIOLACAO_CATEGORIA`, `VIOLACAO_ORCAMENTO`
- API para listar, filtrar e marcar alertas como resolvidos

---

### 📊 Relatórios

- Gastos por funcionário, equipe e departamento
- Dados estruturados para gráficos (séries temporais, agregações)
- Filtros avançados (período, categoria, status)
- Exportação em CSV/JSON

---

### 🏢 Administração

- CRUD de funcionários com hierarquia organizacional
- CRUD de departamentos com configuração de orçamento mensal
- Gestão de categorias e limites (Finance apenas)
- Auditoria automática de ações críticas

---

## **🛠️ Tecnologias Utilizadas**

### **Core**

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA + Hibernate**

### **Banco de Dados**

- **PostgreSQL**
- **Flyway** para versionamento de schema

### **Segurança**

- **Spring Security**
- Autenticação com **usuário e senha (mock)**
- Autorização baseada em **Roles**

### **Documentação**

- **Swagger / OpenAPI**

### **Testes**

- **JUnit 5**
- **Mockito**
- **Testcontainers**

## 🏗️ Arquitetura e Design Patterns

### Clean Architecture

```jsx
┌─────────────────┐
│ Controllers (API REST)         
├─────────────────┤
│  DTOs / Mappers         
├─────────────────┤
│ Services (Use Cases)          
├─────────────────┤
│ Domain (Entities + Rules)          
├─────────────────┤
│ Repositories (Interfaces)        
└─────────────────┘
│
▼
┌─────────────────┐
│    PostgreSQL    
└─────────────────┘
```

## **🗂️ Estrutura de Pastas**

```jsx
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

---

## 🧪 Testes

1. JUnit 5
2. Testcontainers
3. Mockito
   
## 🚀 Execução

### Pré-requisitos

- Java 21
- Docker Desktop

### Execução Rápida

```jsx
bash

# 1. Build
./gradlew clean build -x test

# 2. Subir containers (app + PostgreSQL)
docker compose up --build

# 3. Acessar
# API: http://localhost:8080
# Swagger: http://localhost:8080/swagger-ui.html
# Health: http://localhost:8080/actuator/health
```

### Credenciais de Teste
```
Employee:  employee@ubs.com  / 123456
Manager:   manager@ubs.com   / 123456
Finance:   finance@ubs.com   / 123456
```

### Dados Pré-carregados (Seed)

- **3 Departamentos**: Tecnologia, Financeiro, Comercial
- **Funcionários** vinculados a gestores
- **Categorias** com limites configurados
- **Despesas de exemplo** em diferentes status

---

## 📧 Contatos e Suporte

- **Documentação Frontend:** https://github.com/lucasbeniti/ubs-expense-manager-grupo-7-frontend
- Autores do Projeto:
| Gabriel Lemos Barbosa |
| --- |
| Guilherme Albuquerque de Souza |
| Larissa Navarro Pizarro |
| Lucas André Beniti Bernardo |
| Oscar Thiago Nunes Gomes Ferreira |
