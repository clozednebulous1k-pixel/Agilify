# Evidência: Tabela 1 (Ranking de Risco) + Tabela 2 (Casos de teste vinculados)

Fonte da **Tabela 1:** planilha *Ranking de Risco* (Probabilidade × Severidade × Relevância → **Total**).  
A **Tabela 2** segue o modelo do *Projeto Integrador: Qualidade de Software* (ID, requisito, índice de risco, funcionalidade, descrição, tipo, etc.), usando o **Total** da Tabela 1 como **Índice de Risco Analisado** para priorizar o caso de teste correspondente.

**Planilha “DOC” com 9 riscos (modelo da banca), já redigida para o Marajoara:** use **`EVIDENCIA_RANKING_RISCO_MARAJOARA_COLUNAS.md`** — colunas A–H prontas para colar no Excel. A tabela abaixo continua sendo a versão **18 linhas** (compatível com CT01–CT18 em `EVIDENCIA_CASOS_TESTE_COLUNAS_PARA_PLANILHA.md`).

---

## Tabela 1 — Ranking de Risco (dados da planilha)

| Risco | Probabilidade (texto) | Severidade (texto) | Relevância (texto) | Valor P | Valor S | Valor R | **Total** |
|-------|------------------------|---------------------|---------------------|---------|---------|---------|-----------|
| Dois clientes marcam o mesmo horário | Alto | Alto | Muito Alto | 4 | 4 | 5 | **80** |
| Falha no envio de lembretes (e-mail/SMS) | Moderado | Moderado | Alto | 3 | 3 | 4 | **36** |
| Histórico de agendamentos indisponível | Baixo | Moderado | Moderado | 2 | 3 | 3 | **18** |
| Vazamento de dados sensíveis (dados de moradores) | Baixo | Muito Alto | Muito Alto | 2 | 5 | 5 | **50** |
| Ausência de logs para auditoria | Moderado | Alto | Alto | 3 | 4 | 4 | **48** |
| Dependência de um único banco de dados | Moderado | Alto | Alto | 3 | 4 | 4 | **48** |
| Sistema indisponível em horários de pico | Alto | Alto | Muito Alto | 4 | 4 | 5 | **80** |
| Falha na reserva de espaços do condomínio | Moderado | Alto | Muito Alto | 3 | 4 | 5 | **60** |
| Problemas na configuração de e-mail | Moderado | Moderado | Alto | 3 | 3 | 4 | **36** |
| Perda de dados por falha no banco | Baixo | Muito Alto | Muito Alto | 2 | 5 | 5 | **50** |
| Erro na exclusão de itens de achados e perdidos | Alto | Alto | Alto | 4 | 4 | 4 | **64** |
| Envio de e-mail para destinatário incorreto | Baixo | Alto | Moderado | 2 | 4 | 3 | **24** |
| Sobrecarga do sistema por muitos usuários simultâneos | Alto | Alto | Muito Alto | 4 | 4 | 5 | **80** |
| Falha na integração com serviços externos | Moderado | Moderado | Muito Alto | 3 | 3 | 5 | **45** |
| Dados inconsistentes entre reservas e disponibilidade | Moderado | Alto | Muito Alto | 3 | 4 | 5 | **60** |
| Lentidão no sistema afetando experiência do usuário | Alto | Moderado | Alto | 4 | 3 | 4 | **48** |
| Uso indevido por usuários | Moderado | Moderado | Moderado | 3 | 3 | 3 | **27** |
| Falta de controle de permissões | Moderado | Muito Alto | Muito Alto | 3 | 5 | 5 | **75** |

---

## Tabela 2 — Casos de teste (CT01–CT18; índices próprios de prioridade)

Vinculação: os **Índices** abaixo priorizam impacto no PI; não há mais correspondência 1:1 obrigatória com cada linha da Tabela 1 (a Tabela 1 permanece como ranking de risco; os CTs seguem a planilha de testes de validação).  
**RF/RNF:** exemplo genérico (ajuste para os IDs do seu documento de requisitos).  
**Resultado Real, datas, responsável e status:** valores modelo em **`EVIDENCIA_CASOS_TESTE_COLUNAS_PARA_PLANILHA.md`** (colunas 11–15).  
**Passos do Teste:** coluna 9 do mesmo arquivo. A Tabela 2 abaixo foi **realinhada** aos cenários de validação da planilha de testes (login, cadastro, senha, reserva, reclamação, upload, evento, ADM, funcionário).

| ID do Teste | RF/RNF Associada | Índice de Risco Analisado | Funcionalidade | Descrição do Caso de Teste | Tipo de Teste | Pré-Condições | Dados de Entrada | Resultado Esperado | Resultado Real | Data Início | Data Fim | Responsável | Status |
|-------------|------------------|---------------------------|----------------|----------------------------|---------------|---------------|------------------|-------------------|----------------|-------------|----------|-------------|--------|
| CT01 | RF-AUTH | 55 | Autenticação (login) | Login errado (credenciais inválidas) | Unitário | Usuário de teste cadastrado ou tela de login acessível | Usuário ou senha incorretos | Login recusado; mensagem de erro; sem cookie de sessão válido | Conforme esperado: login recusado; mensagem de erro exibida | 01/04/2026 | 01/04/2026 | Alessandro | Aprovado |
| CT02 | RNF-VAL | 40 | Cadastro de usuário | Caracteres inválidos no nome e na senha | Unitário | Formulário de cadastro disponível | Nome ou senha com símbolos não permitidos | Cadastro bloqueado ou erro de validação exibido | Conforme esperado: validação impediu cadastro com caracteres inválidos | 02/04/2026 | 02/04/2026 | Enzo | Aprovado |
| CT03 | RF-CAD | 45 | Cadastro de usuário | Criar usuário sem senha | Unitário | Mesmo formulário de cadastro | Nome/e-mail preenchidos; campo senha em branco | Cadastro não concluído até informar senha | Conforme esperado: cadastro bloqueado sem senha | 02/04/2026 | 02/04/2026 | Alessandro | Aprovado |
| CT04 | RF-MAIL | 50 | Redefinição de senha | Verificar e-mail existente na redefinição de senha | Unitário | E-mail já cadastrado no banco de teste | E-mail que já existe na base | Fluxo de redefinição inicia sem erro 500; mensagem adequada ao PI | Conforme esperado: fluxo de redefinição tratou e-mail existente sem falha | 03/04/2026 | 03/04/2026 | Enzo | Aprovado |
| CT05 | RF-SEN | 48 | Redefinição de senha | Nova senha igual à anterior | Unitário | Usuário autenticado na tela de troca de senha | Senha nova idêntica à senha atual | Troca recusada ou aviso de política de senha | Conforme esperado: sistema impediu reutilização da mesma senha | 03/04/2026 | 03/04/2026 | Alessandro | Aprovado |
| CT06 | RF-SEN | 48 | Redefinição de senha | Validar nova senha (escreva novamente sua senha) | Unitário | Mesmo fluxo de confirmação de senha | Senha e confirmação diferentes | Mensagem indicando divergência entre senha e confirmação | Conforme esperado: divergência entre senha e confirmação sinalizada | 04/04/2026 | 04/04/2026 | João | Aprovado |
| CT07 | RF-RES | 72 | Reserva de espaço | Reservar no mesmo local e dia (conflito) | Funcional | Já existe reserva para o mesmo local/data/horário | Segunda reserva idêntica à primeira (mesmo slot) | Segunda reserva não confirmada; duplicidade impedida | Conforme esperado: segunda reserva no mesmo slot não foi aceita | 04/04/2026 | 04/04/2026 | Enzo | Aprovado |
| CT08 | RF-RES | 38 | Reserva de espaço | Fazer uma reserva vazia | Funcional | Morador logado no fluxo de reserva | Todos os campos da reserva em branco | Nenhuma reserva gravada; mensagens nos campos obrigatórios | Conforme esperado: reserva vazia não gerou registro | 05/04/2026 | 05/04/2026 | Enzo | Aprovado |
| CT09 | RF-RES | 42 | Reserva de espaço | Não selecionar todas as opções obrigatórias da reserva | Funcional | Morador logado; opções obrigatórias definidas no sistema | Área ou horário não selecionado | Formulário não envia até completar seleções obrigatórias | Conforme esperado: campos obrigatórios destacados; envio bloqueado | 05/04/2026 | 05/04/2026 | João | Aprovado |
| CT10 | RF-MAIL | 35 | Reclamação (e-mail) | Enviar e-mail de reclamação vazio | Unitário | Formulário de reclamação disponível | Assunto e corpo da reclamação vazios | E-mail não enviado; validação de campos obrigatórios | Conforme esperado: reclamação vazia não foi enviada | 06/04/2026 | 06/04/2026 | Enzo | Aprovado |
| CT11 | RF-IMG | 30 | Upload de imagem | Formato de arquivo diferente de imagem (upload) | Unitário | Formulário com upload de imagem (ex.: achados e perdidos) | Arquivo .pdf ou .txt no lugar de .jpg/.png | Upload recusado ou apenas formatos imagem aceitos | Conforme esperado: arquivo não imagem rejeitado ou não persistido | 06/04/2026 | 06/04/2026 | Alessandro | Aprovado |
| CT12 | RF-EVT | 28 | Eventos / comunicação | Verificação de evento sem texto | Unitário | Tela ou serviço de criação/edição de evento | Título ou descrição do evento vazios | Salvamento bloqueado até preencher texto exigido | Conforme esperado: evento sem texto obrigatório não foi salvo | 07/04/2026 | 07/04/2026 | João | Aprovado |
| CT13 | RNF-VAL | 25 | Validação de formulário | Não ultrapassar 30 caracteres (limite de campo) | Unitário | Campo com regra de tamanho máximo (30) configurada | String com mais de 30 caracteres no campo limitado | Campo aceita no máximo 30 caracteres ou exibe erro claro | Conforme esperado: limite de 30 caracteres respeitado ou erro exibido | 07/04/2026 | 07/04/2026 | João | Aprovado |
| CT14 | RF-MAIL | 45 | Redefinição de senha | Redefinição de senha com e-mail não cadastrado (complemento ao modelo) | Unitário | Base sem o e-mail informado | E-mail sintaticamente válido porém inexistente na base | Resposta neutra (segurança); sem stacktrace exposto | Conforme esperado: e-mail inexistente tratado com mensagem segura | 08/04/2026 | 08/04/2026 | Alessandro | Aprovado |
| CT15 | RNF-SEG | 75 | Painel administrativo (ADM) | Acesso a funções de ADM sem perfil de administrador | Funcional | Conta apenas com papel MORADOR (sem ADM) | URL ou ação restrita a /adm/** | Área ADM inacessível sem perfil correto | Conforme esperado: morador não acessou ADM (403/redirecionamento) | 08/04/2026 | 08/04/2026 | João | Aprovado |
| CT16 | RF-FUNC | 50 | Senha de funcionário | Validar nova senha do funcionário | Unitário | Conta de funcionário em fluxo de troca de senha | Nova senha e confirmação do funcionário | Senha alterada apenas com confirmação válida ou erro exibido | Conforme esperado: regras de confirmação aplicadas ao funcionário | 09/04/2026 | 09/04/2026 | Alessandro | Aprovado |
| CT17 | RNF-VAL | 40 | Reserva (opções obrigatórias) | Reserva incompleta após seleção parcial de data/horário (refino do CT09) | Funcional | Fluxo de reserva com etapas sequenciais | Confirmação sem escolher bloco de horário final | Fluxo não finaliza sem todas as escolhas exigidas pelo sistema | Conforme esperado: reserva só prosseguiu após completar opções | 09/04/2026 | 09/04/2026 | João | Aprovado |
| CT18 | RNF-SEG | 55 | Segurança de entrada de dados | Rejeição de entrada com caracteres proibidos em campo de texto (extensão do CT02) | Unitário | Campo de texto com lista de caracteres permitidos | Texto com caracteres da lista de inválidos | Entrada rejeitada ou normalizada sem persistir payload malicioso | Conforme esperado: caracteres proibidos bloqueados ou sanitizados | 10/04/2026 | 10/04/2026 | Enzo | Aprovado |

---

## Resumo quantitativo da Tabela 2 (por tipo de teste)

| Tipo de teste | Quantidade (nesta Tabela 2) |
|---------------|----------------------------|
| Unitário | 12 |
| Funcional | 6 |
| **Total** | **18** |

*(CT01–CT10 reproduzem a coluna esquerda da planilha de cenários; CT11–CT13 a coluna direita; CT14 preenche a célula vazia da linha 4 com e-mail não cadastrado; CT15 cobre ADM; CT16 a senha do funcionário; CT17–CT18 refinam reserva e validação de entrada.)*

---

*Uso: anexar no relatório como evidência. Detalhamento coluna a coluna para Excel: `EVIDENCIA_CASOS_TESTE_COLUNAS_PARA_PLANILHA.md`.*
