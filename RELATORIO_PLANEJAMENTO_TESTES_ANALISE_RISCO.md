Respostas objetivas com base no PI Marajoara (Spring Boot, Thymeleaf, Security, JPA, morador/ADM).

1) Planejamento de testes com mapa de risco em 3 dimensões
As três dimensões usadas são: P (probabilidade: 1 baixa → 3 alta), I (impacto: 1 baixo → 3 alto) e C (categoria: T técnico/produto, S segurança/privacidade, O operação/integrações). O P×I (de 1 a 9) define prioridade de teste.

Exemplos no seu sistema: falta de testes e regressão (R01, P×I alto, T); exposição de rotas ADM (R02, S); alteração indevida de dados em moradores/eventos/reservas (R03, T); upload de imagens em eventos/achados (R04); SMTP para e-mail (R09, O).

2) Planejamento a partir das demandas da análise de risco
Regressão e dados (R01, R03): priorizar unitários nos services e integração nos controllers que persistem (morador, evento, reserva, achados).
Segurança (R02, R07, R08): integração com Spring Security (perfis/roles) e E2E para tentativa de acesso indevido a /adm/**; R07 é mais revisão de processo (não commitar segredos).
Upload e erros (R04, R05): integração com multipart nos endpoints de evento/achado; unitário para busca por ID inexistente nos services.
Ambiente/externos (R06, R09, R10): integração com H2 em teste ou MySQL em container; mock ou GreenMail para e-mail.
3) Unitário
Testa classes isoladas com mocks (ex.: EventoService, MoradorService, ReservaService, MyUserDetailsService). Não sobe servidor nem banco real na forma mais pura.

4) Integração
Testa Spring + camadas juntas: MockMvc ou @SpringBootTest nos controllers, @DataJpaTest nos repositórios, filtros de segurança com usuário fictício. Cobre o fluxo formulário → service → banco (ou quase isso).

5) Ponta a ponta (E2E)
Testa como o usuário: navegador (Playwright/Selenium) — login (quando existir), entrar na área do morador, menus, envio de formulário crítico. Poucos cenários, mas cobrindo jornadas inteiras.

6) Ferramentas indicadas nos planos
Unitário: JUnit 5, Mockito, (opcional) AssertJ.
Integração: Spring Boot Test, MockMvc, @WebMvcTest, @DataJpaTest; Testcontainers (MySQL); GreenMail ou mock de JavaMailSender para e-mail.
E2E: Playwright ou Selenium.
Cobertura: JaCoCo (Maven).
CI: GitHub Actions (ou GitLab CI / Jenkins) rodando mvn test.
7) Resumo quantitativo (planejado por tipo de teste)
(Um mesmo teste pode atender mais de um risco; os totais abaixo são do plano único, não a soma linha a linha por risco.)

Tipo de teste	Quantidade planejada (faixa)
Unitário	70 – 130
Integração	60 – 120
Ponta a ponta (E2E)	18 – 40
Total (completo)	~150 – 290
Meta inicial viável para comprovar no PI: ~40 unitários, ~30 integração, ~10 E2E (~80 casos no total).

Se o relatório exigir só tabela, você pode copiar o bloco acima; o detalhamento por risco (R01–R10) continua no RELATORIO_PLANEJAMENTO_TESTES_ANALISE_RISCO.md e no ANALISE_RISCO_E_TESTES.md.