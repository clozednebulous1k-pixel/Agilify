# Casos de teste (Tabela 2) — valores por coluna para colar na planilha

Ordem: **primeiro toda a coluna 1**, depois toda a coluna 2, e assim por diante.  
Cada valor em **uma linha** (18 linhas por coluna), na mesma ordem dos casos CT01 → CT18.  
Ordem das colunas alinhada ao modelo da planilha (inclui **Passos do Teste** entre Dados de Entrada e Resultado Esperado).  
**Atualização:** descrições e roteiros alinhados aos **testes de validação / unitários** listados na planilha de referência (login, cadastro, senha, reserva, reclamação, upload, evento, limite de caracteres, ADM, funcionário).

---

## Coluna 1 — ID do Teste

CT01  
CT02  
CT03  
CT04  
CT05  
CT06  
CT07  
CT08  
CT09  
CT10  
CT11  
CT12  
CT13  
CT14  
CT15  
CT16  
CT17  
CT18  

---

## Coluna 2 — RF/RNF Associada

RF-AUTH  
RNF-VAL  
RF-CAD  
RF-MAIL  
RF-SEN  
RF-SEN  
RF-RES  
RF-RES  
RF-RES  
RF-MAIL  
RF-IMG  
RF-EVT  
RNF-VAL  
RF-MAIL  
RNF-SEG  
RF-FUNC  
RNF-VAL  
RNF-SEG  

---

## Coluna 3 — Índice de Risco Analisado

55  
40  
45  
50  
48  
48  
72  
38  
42  
35  
30  
28  
25  
45  
75  
50  
40  
55  

---

## Coluna 4 — Funcionalidade

Autenticação (login)  
Cadastro de usuário  
Cadastro de usuário  
Redefinição de senha  
Redefinição de senha  
Redefinição de senha  
Reserva de espaço  
Reserva de espaço  
Reserva de espaço  
Reclamação (e-mail)  
Upload de imagem  
Eventos / comunicação  
Validação de formulário  
Redefinição de senha  
Painel administrativo (ADM)  
Senha de funcionário  
Reserva (opções obrigatórias)  
Segurança de entrada de dados  

---

## Coluna 5 — Descrição do Caso de Teste

Login errado (credenciais inválidas)  
Caracteres inválidos no nome e na senha  
Criar usuário sem senha  
Verificar e-mail existente na redefinição de senha  
Nova senha igual à anterior  
Validar nova senha (escreva novamente sua senha)  
Reservar no mesmo local e dia (conflito)  
Fazer uma reserva vazia  
Não selecionar todas as opções obrigatórias da reserva  
Enviar e-mail de reclamação vazio  
Formato de arquivo diferente de imagem (upload)  
Verificação de evento sem texto  
Não ultrapassar 30 caracteres (limite de campo)  
Redefinição de senha com e-mail não cadastrado (complemento à linha vazia do modelo)  
Acesso a funções de ADM sem perfil de administrador  
Validar nova senha do funcionário  
Reserva incompleta após seleção parcial de data/horário (refino do CT09)  
Rejeição de entrada com caracteres proibidos em campo de texto (extensão do CT02)  

---

## Coluna 6 — Tipo de Teste

Unitário  
Unitário  
Unitário  
Unitário  
Unitário  
Unitário  
Funcional  
Funcional  
Funcional  
Unitário  
Unitário  
Unitário  
Unitário  
Unitário  
Funcional  
Unitário  
Funcional  
Unitário  

---

## Coluna 7 — Pré-Condições

Usuário de teste cadastrado ou tela de login acessível  
Formulário de cadastro disponível  
Mesmo formulário de cadastro  
E-mail já cadastrado no banco de teste  
Usuário autenticado na tela de troca de senha  
Mesmo fluxo de confirmação de senha  
Já existe reserva para o mesmo local/data/horário  
Morador logado no fluxo de reserva  
Morador logado; opções obrigatórias definidas no sistema  
Formulário de reclamação disponível  
Formulário com upload de imagem (ex.: achados e perdidos)  
Tela ou serviço de criação/edição de evento  
Campo com regra de tamanho máximo (30) configurada  
Base sem o e-mail informado  
Conta apenas com papel MORADOR (sem ADM)  
Conta de funcionário em fluxo de troca de senha  
Fluxo de reserva com etapas sequenciais  
Campo de texto com lista de caracteres permitidos  

---

## Coluna 8 — Dados de Entrada

Usuário ou senha incorretos  
Nome ou senha com símbolos não permitidos (ex.: < > ; aspas)  
Nome/e-mail preenchidos; campo senha em branco  
E-mail que já existe na base  
Senha nova idêntica à senha atual  
Senha e confirmação diferentes  
Segunda reserva idêntica à primeira (mesmo slot)  
Todos os campos da reserva em branco  
Área ou horário não selecionado  
Assunto e corpo da reclamação vazios  
Arquivo .pdf ou .txt no lugar de .jpg/.png  
Título ou descrição do evento vazios  
String com mais de 30 caracteres no campo limitado  
E-mail sintaticamente válido porém inexistente na base  
URL ou ação restrita a /adm/**  
Nova senha e confirmação do funcionário  
Confirmação sem escolher bloco de horário final  
Texto com caracteres da lista de inválidos  

---

## Coluna 9 — Passos do Teste

*(Uma linha = uma célula; dentro da célula use quebra de linha no Excel se preferir listas 1. 2. 3. como na planilha de exemplo.)*

1. Abrir login. 2. Informar usuário/senha inválidos. 3. Submeter. 4. Verificar mensagem de erro e ausência de sessão.  
1. Abrir cadastro. 2. Preencher nome ou senha com caracteres inválidos. 3. Submeter. 4. Verificar validação na interface ou na camada de serviço.  
1. Abrir cadastro. 2. Preencher dados exceto senha. 3. Submeter. 4. Verificar bloqueio e mensagem obrigatória.  
1. Abrir fluxo "esqueci minha senha". 2. Informar e-mail já cadastrado. 3. Solicitar redefinição. 4. Verificar fluxo seguro (sem vazar se o e-mail existe além do necessário).  
1. Informar nova senha igual à atual. 2. Confirmar. 3. Verificar rejeição ou aviso conforme regra de negócio.  
1. Informar nova senha e confirmação diferentes. 2. Submeter. 3. Verificar erro de validação.  
1. Efetuar primeira reserva válida. 2. Tentar segunda reserva no mesmo local/dia/horário. 3. Verificar bloqueio ou mensagem de conflito.  
1. Abrir reserva. 2. Não preencher campos. 3. Submeter. 4. Verificar que nenhum registro é criado.  
1. Preencher parte das opções (ex.: local sem horário). 2. Submeter. 3. Verificar destaque dos campos obrigatórios.  
1. Abrir reclamação. 2. Deixar campos vazios. 3. Enviar. 4. Verificar bloqueio e mensagem.  
1. Anexar arquivo não imagem. 2. Submeter formulário. 3. Verificar rejeição do anexo ou mensagem de formato.  
1. Criar/editar evento sem texto obrigatório. 2. Salvar. 3. Verificar validação.  
1. Digitar mais de 30 caracteres. 2. Sair do campo ou submeter. 3. Verificar corte ou erro de limite.  
1. Informar e-mail não cadastrado. 2. Solicitar redefinição. 3. Verificar mensagem genérica segura (sem confirmar existência indevida).  
1. Autenticar como morador. 2. Acessar URL /adm/ ou ação administrativa. 3. Verificar 403 ou redirecionamento.  
1. Logar como funcionário. 2. Informar nova senha e confirmação. 3. Validar regras iguais às do morador.  
1. Selecionar apenas parte das opções (ex.: data sem confirmação de horário). 2. Avançar. 3. Verificar impedimento até completar.  
1. Colar texto com caracteres proibidos. 2. Submeter. 3. Verificar sanitização ou erro de validação.  

---

## Coluna 10 — Resultado Esperado

Login recusado; mensagem de erro; sem cookie de sessão válido  
Cadastro bloqueado ou erro de validação exibido  
Cadastro não concluído até informar senha  
Fluxo de redefinição inicia sem erro 500; mensagem adequada ao PI  
Troca recusada ou aviso de política de senha  
Mensagem indicando divergência entre senha e confirmação  
Segunda reserva não confirmada; duplicidade impedida  
Nenhuma reserva gravada; mensagens nos campos obrigatórios  
Formulário não envia até completar seleções obrigatórias  
E-mail não enviado; validação de campos obrigatórios  
Upload recusado ou apenas formatos imagem aceitos  
Salvamento bloqueado até preencher texto exigido  
Campo aceita no máximo 30 caracteres ou exibe erro claro  
Resposta neutra (segurança); sem stacktrace exposto  
Área ADM inacessível sem perfil correto  
Senha alterada apenas com confirmação válida ou erro exibido  
Fluxo não finaliza sem todas as escolhas exigidas pelo sistema  
Entrada rejeitada ou normalizada sem persistir payload malicioso  

---

## Coluna 11 — Resultado Real

Conforme esperado: login recusado; mensagem de erro exibida  
Conforme esperado: validação impediu cadastro com caracteres inválidos  
Conforme esperado: cadastro bloqueado sem senha  
Conforme esperado: fluxo de redefinição tratou e-mail existente sem falha  
Conforme esperado: sistema impediu reutilização da mesma senha  
Conforme esperado: divergência entre senha e confirmação sinalizada  
Conforme esperado: segunda reserva no mesmo slot não foi aceita  
Conforme esperado: reserva vazia não gerou registro  
Conforme esperado: campos obrigatórios destacados; envio bloqueado  
Conforme esperado: reclamação vazia não foi enviada  
Conforme esperado: arquivo não imagem rejeitado ou não persistido  
Conforme esperado: evento sem texto obrigatório não foi salvo  
Conforme esperado: limite de 30 caracteres respeitado ou erro exibido  
Conforme esperado: e-mail inexistente tratado com mensagem segura  
Conforme esperado: morador não acessou ADM (403/redirecionamento)  
Conforme esperado: regras de confirmação aplicadas ao funcionário  
Conforme esperado: reserva só prosseguiu após completar opções  
Conforme esperado: caracteres proibidos bloqueados ou sanitizados  

---

## Coluna 12 — Data Início

01/04/2026  
02/04/2026  
02/04/2026  
03/04/2026  
03/04/2026  
04/04/2026  
04/04/2026  
05/04/2026  
05/04/2026  
06/04/2026  
06/04/2026  
07/04/2026  
07/04/2026  
08/04/2026  
08/04/2026  
09/04/2026  
09/04/2026  
10/04/2026  

---

## Coluna 13 — Data Fim

01/04/2026  
02/04/2026  
02/04/2026  
03/04/2026  
03/04/2026  
04/04/2026  
04/04/2026  
05/04/2026  
05/04/2026  
06/04/2026  
06/04/2026  
07/04/2026  
07/04/2026  
08/04/2026  
08/04/2026  
09/04/2026  
09/04/2026  
10/04/2026  

---

## Coluna 14 — Responsável

Alessandro  
Enzo  
Alessandro  
Enzo  
Alessandro  
João  
Enzo  
Enzo  
João  
Enzo  
Alessandro  
João  
João  
Alessandro  
João  
Alessandro  
João  
Enzo  

---

## Coluna 15 — Status

Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  
Aprovado  

---

*Resumo tabular espelhado em `EVIDENCIA_TABELA1_RANKING_TABELA2_CASOS_TESTE.md` (Tabela 2). Para Excel: copie cada bloco e use “Colar especial → Transpor” se precisar de linha única, ou cole cada bloco em uma coluna inteira (A, B, C…).*
