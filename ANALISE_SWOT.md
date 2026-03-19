# Análise SWOT – Sistema Condomínio Marajoara (PI)

## Forças (Strengths)

1. **Stack moderna e consolidada** – Uso de Java 24, Spring Boot 3.4.5 e Spring Security oferece segurança, manutenção e ecossistema maduro, além de boa base para evolução e contratação de desenvolvedores.

2. **Separação de perfis e controle de acesso** – Diferenciação clara entre Morador, ADM e Funcionário com rotas protegidas por papel (Spring Security), reduzindo risco de acesso indevido a áreas sensíveis.

3. **Funcionalidades alinhadas ao dia a dia do condomínio** – Cobertura de eventos, achados e perdidos, reserva/aluguel de áreas, reclamações e perfil do morador, atendendo demandas comuns de síndicos e moradores.

4. **Integração com e-mail** – Uso de Spring Mail para recuperação de senha e notificações melhora a experiência do usuário e a comunicação do condomínio sem depender só do acesso ao sistema.

---

## Fraquezas (Weaknesses)

1. **Credenciais fixas no código** – Senha do banco em `DataConfiguration.java` e dados de e-mail em `application.properties` expõem segredos no repositório e dificultam ambientes (dev/homolog/prod) e boas práticas de segurança.

2. **Ausência de camada de API REST** – Foco apenas em Thymeleaf e fluxo web; não há endpoints REST documentados, o que limita integrações (app mobile, terceiros, dashboards) e testes automatizados via API.

3. **Dependência de um único banco** – Configuração atada a MySQL e `DriverManagerDataSource` sem uso de connection pool (ex.: HikariCP) pode impactar desempenho e resiliência sob carga.

4. **Pouca rastreabilidade e auditoria** – Não há logs estruturados de ações sensíveis (login, alteração de dados, exclusões), dificultando investigação de incidentes e conformidade.

---

## Oportunidades (Opportunities)

1. **App mobile ou PWA** – Criar uma API REST e um app ou PWA permitiria notificações push, reservas e reclamações pelo celular, aumentando adesão dos moradores e valor percebido do condomínio.

2. **Integração com pagamento e boletos** – Inclusão de cobrança de taxas, multas e reservas (gateways de pagamento ou geração de boleto) centralizaria finanças e reduziria trabalho manual da administração.

3. **Dashboard e relatórios** – Relatórios de reclamações, uso de áreas comuns, eventos e métricas de satisfação dariam subsídio para decisões do síndico e da assembleia, diferenciando o sistema no mercado.

4. **Configuração por ambiente e DevOps** – Uso de variáveis de ambiente ou perfis Spring (dev/test/prod) para banco e e-mail, além de Docker/CI, facilitaria deploy, testes e escalabilidade.

---

## Riscos (Threats)

1. **Riscos de segurança e vazamento de dados** – Credenciais no código, possível falta de rate limiting no login e de políticas de senha forte aumentam risco de invasão e vazamento de dados pessoais (LGPD).

2. **Concorrência e commoditização** – Sistemas de condomínio e portais de gestão estão se tornando comuns; sem diferenciação (app, pagamentos, relatórios, UX), o sistema pode perder relevância ou valor comercial.

3. **Dependência de serviços externos** – Uso de Gmail SMTP e MySQL local pode gerar indisponibilidade ou mudanças de política (limites de envio, custo de hospedagem), afetando operação do condomínio.

4. **Manutenção e evolução do stack** – Java 24 e Spring Boot 3.4 são recentes; upgrades futuros de bibliotecas e JDK podem exigir refatoração e testes, principalmente se a base de código e testes automatizados forem pequenos.

---

*Análise baseada na revisão do código-fonte do projeto PI Marajoara (Spring Boot, Thymeleaf, Spring Security, MySQL).*
