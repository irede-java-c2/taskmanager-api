# 📋 Task Manager API

API REST para gerenciamento de tarefas, desenvolvida como **projeto final do Módulo Avançado** de Java da **iRede**.

> 🚧 **Projeto em construção:** este repositório evolui a cada aula. Novas funcionalidades e documentação serão adicionadas ao longo do módulo.

---

## 🛠️ Tecnologias

- Java 21
- Spring Boot 3
- Maven

## 📦 Informações do projeto

| Propriedade  | Valor                                                                        |
|--------------|------------------------------------------------------------------------------|
| groupId      | `irede.org.br`                                                               |
| artifactId   | `taskmanager-api`                                                            |
| Repositório  | [irede-java-c2/taskmanager-api](https://github.com/irede-java-c2/taskmanager-api) |

## ✅ Pré-requisitos

- JDK 21 ou superior
- Maven 3.9+ (ou use o Maven Wrapper incluso no projeto)
- Git

## ▶️ Como executar

```bash
# Clone o repositório
git clone https://github.com/irede-java-c2/taskmanager-api.git

# Acesse a pasta do projeto
cd taskmanager-api

# Execute a aplicação
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`.

A aplicação ficará disponível em: `http://localhost:8080`

## 📍 Estado do projeto

> Atualizado por quem fecha cada fase. Quem assume a próxima aula lê esta seção na véspera.

| Item | Situação |
|------|----------|
| Última aula concluída | — (o módulo começa em 27/10/2026) |
| Última tag | — |
| Fase atual | Fase 1 · Persistência (Alex) |
| Próximo marco | Marco 1 · 12/11/2026 — entidades, relações e consultas JPA prontas |

**Pendências para a próxima aula:** nenhuma.

## 🗂️ Fases e versões

| Fase | Aulas | Professor | Marco | Release |
|------|-------|-----------|-------|---------|
| 1 · Persistência | 01 a 06 | Alex | 12/11/2026 | `v0.1.0` |
| 2 · API REST | 07 a 12 | David | 03/12/2026 | `v0.2.0` |
| 3 · Segurança e docs | 13 a 15 | Alex | 15/12/2026 | `v0.3.0` |
| 4 · Entrega | 16 a 18 | Alex e David | 07/01/2027 | `v1.0.0` |

O acompanhamento das tarefas fica no [quadro do projeto](https://github.com/orgs/irede-java-c2/projects/1).

## 🏷️ Como acompanhar as aulas

Cada aula termina com uma tag `aula-NN` na `main`. Se você faltou ou se perdeu, volte ao estado exato do fim daquela aula:

```bash
git fetch --tags
git checkout aula-06
```

Para voltar ao código mais recente: `git checkout main`.

## 🏫 Instituição

Projeto desenvolvido durante o curso de Java da **iRede**.

## 👨‍💻 Autores

**David Silva** — [github.com/davsilvam](https://github.com/davsilvam)

**Alex Girão** — [github.com/alex-girao](https://github.com/alex-girao)
