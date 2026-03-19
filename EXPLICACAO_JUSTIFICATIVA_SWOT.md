# Explicação e Justificativa da Análise SWOT – PI Marajoara

## Explicação da análise SWOT

A **análise SWOT** é uma ferramenta de planejamento que organiza o diagnóstico do projeto em quatro dimensões:

- **S (Strengths – Forças):** pontos positivos e vantagens atuais do sistema.
- **W (Weaknesses – Fraquezas):** pontos negativos e limitações atuais.
- **O (Opportunities – Oportunidades):** tendências e possibilidades de melhoria no futuro.
- **T (Threats – Ameaças):** riscos e fatores externos que podem prejudicar o projeto.

No caso do **Sistema Condomínio Marajoara (PI)**, a SWOT foi feita com base na **revisão do código-fonte** (Java, Spring Boot, Thymeleaf, Spring Security, MySQL), na **arquitetura** (controllers, services, repositórios, configurações) e nas **funcionalidades** implementadas (login, eventos, achados e perdidos, reservas, reclamações, perfis). O objetivo é dar uma visão objetiva do estado atual do software e do que pode ser feito a partir daí.

---

## Justificativa por quadrante

### Forças (S) – Por que esses pontos foram considerados forças

1. **Stack moderna e consolidada**  
   O projeto utiliza **Java 17/24**, **Spring Boot 3.4** e **Spring Security**, tecnologias amplamente usadas no mercado, com documentação e comunidade ativas. Isso **justifica-se** pelo fato de o código estar organizado em camadas (Controller, Service, Repository), usar injeção de dependência e seguir padrões que facilitam manutenção e contratação de desenvolvedores familiarizados com o ecossistema Java/Spring.

2. **Separação de perfis e controle de acesso**  
   No `SecurityConfig` há regras explícitas para `/morador/**`, `/adm/**` e rotas públicas. **Justificativa:** a existência de roles (ROLE_MORADOR, ROLE_ADM, ROLE_FUNCIONARIO) e de um `CustomLoginSuccessHandler` que redireciona por perfil mostra que o sistema foi pensado para segregar acesso, o que é uma força em termos de segurança e organização do negócio (condomínio).

3. **Funcionalidades alinhadas ao dia a dia do condomínio**  
   Controllers e telas cobrem **eventos**, **achados e perdidos**, **reserva/aluguel**, **reclamações** e **perfil**. **Justificativa:** essas funcionalidades correspondem a necessidades reais de síndicos e moradores (comunicação, itens perdidos, uso de áreas comuns, canal de reclamações), o que torna o sistema útil e aderente ao domínio.

4. **Integração com e-mail**  
   O uso de **Spring Mail** e configuração em `application.properties` para envio de e-mails. **Justificativa:** permite recuperação de senha e notificações sem depender apenas do acesso ao sistema, melhorando a experiência e a comunicação do condomínio.

---

### Fraquezas (W) – Por que esses pontos foram considerados fraquezas

1. **Credenciais fixas no código**  
   Em `DataConfiguration.java` há usuário e senha do banco definidos no código; em `application.properties` há dados de e-mail. **Justificativa:** isso viola boas práticas de segurança (segredos não devem estar no repositório), dificulta ambientes diferentes (dev/homolog/prod) e aumenta o risco em caso de vazamento do código.

2. **Ausência de camada de API REST**  
   O projeto expõe apenas páginas Thymeleaf e formulários; não há controllers REST com JSON documentados. **Justificativa:** sem API, fica difícil integrar um app mobile, terceiros ou ferramentas de dashboard, e também automatizar testes via HTTP.

3. **Dependência de um único banco e configuração de conexão**  
   A configuração de dados estava inicialmente atada a MySQL com `DriverManagerDataSource`. **Justificativa:** não usar connection pool (como HikariCP, que o Spring Boot já oferece) pode limitar desempenho e resiliência quando houver mais usuários ou carga.

4. **Pouca rastreabilidade e auditoria**  
   Não foram identificados logs estruturados de login, alterações ou exclusões. **Justificativa:** em caso de incidente ou dúvida sobre “quem fez o quê”, a falta de auditoria dificulta investigação e atendimento à LGPD.

---

### Oportunidades (O) – Por que esses pontos são oportunidades

1. **App mobile ou PWA**  
   **Justificativa:** o sistema já resolve problemas do condomínio (eventos, reservas, reclamações). Um app ou PWA ampliaria o uso pelos moradores (notificações, acesso pelo celular) e poderia ser um diferencial comercial.

2. **Integração com pagamento e boletos**  
   **Justificativa:** condomínios precisam cobrar taxas, multas e reservas; hoje isso costuma ser manual. Incluir pagamento ou geração de boleto no próprio sistema reduz trabalho da administração e agrega valor.

3. **Dashboard e relatórios**  
   **Justificativa:** os dados já existem (eventos, reclamações, reservas). Transformá-los em relatórios e indicadores ajudaria o síndico e a assembleia a tomar decisões e diferenciaria o produto no mercado.

4. **Configuração por ambiente e DevOps**  
   **Justificativa:** usar variáveis de ambiente, perfis Spring (dev/test/prod) e ferramentas como Docker/CI tornaria o deploy e os testes mais seguros e repetíveis, preparando o sistema para crescimento.

---

### Ameaças (T) – Por que esses pontos são ameaças

1. **Riscos de segurança e vazamento de dados**  
   **Justificativa:** credenciais no código e possível ausência de rate limiting e política de senha forte aumentam o risco de ataques e vazamentos. A LGPD exige cuidado com dados pessoais; falhas podem gerar sanções e perda de confiança.

2. **Concorrência e commoditização**  
   **Justificativa:** há vários sistemas e portais para condomínios. Sem diferenciação clara (app, pagamentos, relatórios, UX), o sistema pode ficar “mais um” e perder relevância ou valor comercial.

3. **Dependência de serviços externos**  
   **Justificativa:** Gmail SMTP e MySQL (ou outro banco externo) podem mudar políticas, limites ou custos. Indisponibilidade ou mudança de regras afetam diretamente a operação do condomínio que usar o sistema.

4. **Manutenção e evolução do stack**  
   **Justificativa:** Java e Spring Boot evoluem; bibliotecas e JDK têm ciclos de suporte. Com poucos testes automatizados, upgrades futuros podem exigir refatoração e testes manuais, aumentando custo e risco.

---

## Conclusão

A análise SWOT do **PI Marajoara** foi construída a partir do que o código e a arquitetura mostram hoje: **forças** no uso de tecnologias sólidas e em funcionalidades alinhadas ao condomínio; **fraquezas** em segurança de configuração, ausência de API e auditoria; **oportunidades** em mobile, pagamentos, relatórios e DevOps; **ameaças** em segurança, concorrência, dependência de terceiros e evolução do stack.

Ela serve como **base para decisões** (o que priorizar, o que melhorar primeiro) e para **comunicação** do estado do projeto (relatórios, apresentações, planejamento acadêmico ou de produto).

*Documento complementar à análise SWOT do Sistema Condomínio Marajoara (PI).*
