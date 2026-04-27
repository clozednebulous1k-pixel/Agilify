# Análise de risco e estimativa de testes – PI Marajoara

Documento baseado no código atual do projeto (controllers, services, repositórios, segurança e configuração de dados).

---

## 1. Visão geral do escopo (para embasar riscos e testes)

| Área | Quantidade aproximada |
|------|------------------------|
| Controllers (páginas + APIs de formulário/imagem) | ~10 classes |
| Endpoints mapeados (GET/POST) | **~37** (inclui imagens e redirects) |
| Services | **8** |
| Repositories JPA | **6** |
| Entidades principais | Morador, ADM, Funcionário, Evento, Reserva, AchadosPerdidos, etc. |
| Testes automatizados hoje | **1** (`contextLoads` em `PiApplicationTests`) |

---

## 2. Análise de risco

### 2.1 Riscos técnicos e de produto

| Risco | Descrição | Severidade | Mitigação sugerida |
|-------|-----------|------------|-------------------|
| **Falta de cobertura de testes** | Quase não há testes além do carregamento do contexto; regressões passam despercebidas. | Alta | Priorizar testes de integração nos fluxos críticos e E2E nos caminhos principais. |
| **Upload de arquivos** | Eventos e achados usam `MultipartFile`; erros de tipo/tamanho podem quebrar ou persistir dados inconsistentes. | Média | Testes de integração nos controllers de upload + validação de tamanho/tipo. |
| **Exceções genéricas** | Ex.: `RuntimeException` em `getById` sem tratamento uniforme para o usuário. | Média | Tratamento global (`@ControllerAdvice`) + testes de serviço para IDs inexistentes. |
| **Dois modos de banco (H2 / MySQL)** | Comportamento pode divergir entre ambientes (DDL, dialectos). | Média | CI com perfil `mysql` em container ou testes de integração explícitos por perfil. |

### 2.2 Segurança e privacidade

| Risco | Descrição | Severidade | Mitigação sugerida |
|-------|-----------|------------|-------------------|
| **Acesso liberado (cenário atual)** | Se `permitAll()` cobrir tudo, rotas de ADM ficam acessíveis sem autenticação real. | **Alta** | Reativar regras por perfil em produção; testes E2E de negação de acesso. |
| **Dados sensíveis em configuração** | Senhas e e-mail devem vir de variáveis de ambiente (já parcialmente adotado). | Média | Nunca commitar segredos; revisar `application.properties` e scripts SQL. |
| **LGPD** | Dados de moradores (e-mail, nome) sem auditoria clara de quem acessou/alterou. | Média | Logs de auditoria + políticas de retenção (fora do escopo imediato do código). |

### 2.3 Operação e integrações

| Risco | Descrição | Severidade | Mitigação sugerida |
|-------|-----------|------------|-------------------|
| **E-mail (SMTP)** | Falha de envio não coberta por testes; dependência de Gmail/limites. | Média | Testes de integração com SMTP fake (GreenMail) ou mocks em `MailService`. |
| **Disponibilidade do banco** | Sem pool explícito no `DataConfiguration` (MySQL); sob carga pode degradar. | Baixa–Média | HikariCP via Spring Boot + testes de carga pontuais. |

### 2.4 Resumo de prioridade

1. **Crítico:** segurança de rotas em produção + fluxos que alteram dados (morador, ADM, reservas, eventos).  
2. **Alto:** persistência e uploads.  
3. **Médio:** e-mail, consistência entre perfis H2/MySQL.  

---

## 3. Estimativa de quantidade de testes

Os números abaixo são **faixas** (mínimo razoável × cenário mais completo), não um “plano fechado”. A equipe pode priorizar o mínimo nos fluxos de maior risco.

### 3.1 Testes unitários

**O quê:** métodos públicos de **services** com dependências mockadas (Mockito), regras de negócio, e eventualmente helpers puros.

| Componente | Estimativa (faixa) |
|------------|-------------------|
| 8 services (média ~6–10 casos por service: happy path, nulos, exceções) | **50 – 90** |
| `MyUserDetailsService` / `UsuarioLogado` (roles, usuário inexistente) | **8 – 15** |
| `CustomLoginSuccessHandler` (redirects por role) | **4 – 8** |
| Pequenos utilitários / validações se existirem | **5 – 15** |

**Total unitário (ordem de grandeza):** **~70 – 130** casos de teste.

*Observação:* não é obrigatório “um teste por linha”; agrupar por comportamento reduz a contagem sem perder cobertura útil.

### 3.2 Testes de integração

**O quê:** Spring Boot com contexto parcial ou completo — `@WebMvcTest` + `@MockBean` para controllers; `@DataJpaTest` para repositórios; `@SpringBootTest` + Testcontainers ou H2 para fluxo controller → service → repository.

| Componente | Estimativa (faixa) |
|------------|-------------------|
| Repositórios JPA (CRUD básico + queries customizadas se houver) | **12 – 24** |
| Controllers “Info*” (POST/GET com multipart onde couber) | **25 – 45** |
| Controllers de página (GET que retorna view name) | **10 – 20** |
| `SecurityFilterChain` (regras por URL/role) — quando segurança estiver ativa | **8 – 20** |
| Carregamento de imagens (`imgEv`, `imgAc`) | **4 – 8** |

**Total integração (ordem de grandeza):** **~60 – 120** casos de teste.

*Observação:* muitos GETs de página podem ser agrupados em poucos testes que verificam status e nome da view.

### 3.3 Testes E2E (ponta a ponta)

**O quê:** navegador ou API HTTP contra app subindo (Playwright, Selenium, Cypress ou RestAssured + HTML). Focam em **jornadas de usuário**, não em cada método.

| Jornada | Estimativa (faixa) |
|---------|-------------------|
| Entrada na home → entrar → listar eventos (morador) | **2 – 4** |
| Navegação morador: achados, aluguel, reclamações, perfil | **4 – 8** |
| Fluxo ADM: login (se existir) → eventos / achados / configurações | **4 – 10** |
| Criação de reserva / evento (form + redirect) | **3 – 6** |
| Upload de imagem (evento/achado) — smoke | **2 – 4** |
| Regressão de segurança (403 em rota protegida, quando aplicável) | **3 – 8** |

**Total E2E (ordem de grandeza):** **~18 – 40** cenários.

*Observação:* E2E são caros de manter; costuma-se ter **menos** testes E2E que integração, cobrindo só os fluxos que geram valor ou risco.

---

## 4. Resumo numérico (referência rápida)

| Tipo | Estimativa aproximada |
|------|------------------------|
| **Unitários** | **70 – 130** |
| **Integração** | **60 – 120** |
| **E2E** | **18 – 40** |
| **Total (projeto bem coberto)** | **~150 – 290** casos |

Para um **MVP de qualidade** (não cobertura máxima), uma meta realista seria algo como:

- **Unitários:** 30–50 (prioridade: services + segurança)
- **Integração:** 25–40 (prioridade: POSTs que alteram dados + repositórios)
- **E2E:** 8–15 (2–3 jornadas críticas)

---

## 5. Como usar este documento

- **Análise de risco:** priorize mitigação onde a severidade é **Alta** (segurança e dados) antes de expandir testes cosméticos.  
- **Estimativa de testes:** use as faixas para planejar sprint/PI; ajuste conforme decisão de cobertura mínima (ex.: 60% linhas em services).

*Última revisão baseada na estrutura atual do repositório PI Marajoara.*
