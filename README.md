# CareerOS Platform

**CareerOS** é um Sistema Operacional da Carreira — uma plataforma inteligente para gestão completa da vida profissional de desenvolvedores de software.

> Produto: **CareerOS** | Repositório: **CareerOS Platform** | Arquitetura: **Monólito Modular (MVP)**

## Deploy em produção

| Serviço | URL | Host |
|---------|-----|------|
| Frontend (Angular) | https://careeros-web-mu.vercel.app | Vercel |
| Backend (Spring Boot API) | https://backend-production-29505.up.railway.app | Railway |
| Python AI Service | https://python-service-production-c7af.up.railway.app | Railway |
| Banco de dados | PostgreSQL gerenciado | Supabase (sa-east-1) |

## Visão

Centralizar todas as informações profissionais, utilizar IA para gerar documentos personalizados, acompanhar vagas e organizar todo o processo de recolocação — **sem violar Termos de Uso** de plataformas como LinkedIn.

O envio de candidaturas e mensagens **sempre requer confirmação explícita do usuário**.

## Stack

| Camada | Tecnologias |
|--------|-------------|
| Backend | Java 21, Spring Boot 3.4, Spring Security, Spring Data JPA, PostgreSQL |
| Frontend | Angular 19, Angular Material |
| IA | Python, FastAPI, LangChain, OpenAI API (adaptável) |
| Automação | Python, Playwright (apenas quando permitido) |
| Infra | Docker Compose, Prometheus, Grafana |

## Arquitetura

```
careeros-platform/
├── backend/           # Monólito modular Java (14 módulos)
├── frontend/          # Angular SPA
├── python-service/    # Serviço de IA multiagente
├── automation/        # Playwright (preenchimento assistido)
├── infra/             # Prometheus, Grafana
└── docs/              # Documentação arquitetural
```

### Módulos Backend

`auth` · `profile` · `linkedin` · `resume` · `jobs` · `recruiter` · `ai` · `statistics` · `notification` · `diary` · `achievements` · `skills`

### Agentes de IA

`profile` · `resume` · `linkedin` · `jobs` · `ats` · `diary` · `interview` · `mentor`

## Funcionalidades (Roadmap)

### MVP (esta versão)
- [x] Estrutura modular do monólito
- [x] Perfil Mestre (API)
- [x] Diário de Carreira (API) — **diferencial**
- [x] Gateway de agentes IA
- [x] Frontend Angular com navegação
- [x] Docker Compose
- [x] Swagger / OpenAPI
- [x] Migrações Flyway

### Próximas entregas
- [ ] OAuth2 + JWT completo
- [ ] Conectores de vagas (Gupy, Indeed, Revelo, GeekHunter)
- [ ] Gerador de currículos por vaga
- [ ] Simulador ATS
- [ ] Simulador de entrevistas
- [ ] Banco de Conquistas
- [ ] Mapa de Competências
- [ ] Radar do Mercado
- [ ] Roadmap Inteligente de estudos

## Quick Start

### Pré-requisitos

- Docker & Docker Compose
- Java 21 + Maven (desenvolvimento backend)
- Node.js 20+ (desenvolvimento frontend)
- Python 3.12+ (desenvolvimento IA)

### 1. Configurar ambiente

```bash
cp .env.example .env
# Edite OPENAI_API_KEY e JWT_SECRET
```

### 2. Subir com Docker

```bash
docker compose up -d
```

| Serviço | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Python AI | http://localhost:8000 |
| PostgreSQL | localhost:5432 |

### 3. Desenvolvimento local

```bash
# Backend
cd backend && mvn spring-boot:run -pl careeros-app

# Python AI
cd python-service && pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000

# Frontend
cd frontend/careeros-web && npm install && npm start
```

## API

Documentação interativa em `/swagger-ui.html` após iniciar o backend.

### Endpoints principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/v1/profile/me` | Perfil mestre |
| PUT | `/api/v1/profile/me` | Atualizar perfil |
| POST | `/api/v1/diary` | Nova entrada no diário |
| GET | `/api/v1/diary/search?q=` | Pesquisar atividades |
| POST | `/api/v1/ai/agents/{agent}` | Invocar agente IA |

## Princípios

- **Clean Architecture** e **SOLID**
- **DDD** onde faz sentido
- Preparado para **Event-Driven** e **multi-tenant**
- **Modular Monolith** primeiro, microsserviços quando necessário
- Automação ética: APIs oficiais > scraping permitido > confirmação humana

## Testes

```bash
# Backend
cd backend && mvn test

# Python
cd python-service && pytest

# Frontend
cd frontend/careeros-web && npm test
```

## Licença

Projeto de portfólio — uso pessoal e demonstração técnica.

---

Desenvolvido como demonstração de competências em Java, Spring Boot, Angular, Python, IA, Arquitetura de Software e Engenharia de Prompt.
