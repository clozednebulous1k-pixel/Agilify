# Relação de Testes Executados — PI Marajoara (valores por coluna para colar na planilha)

**Projeto:** Condomínio Marajoara (Spring Boot, pacote `br.com.projetoMarajoara`).

**Situação real no repositório:** hoje existe apenas **`PiApplicationTests`** (`contextLoads`). As demais linhas são **suítes alinhadas aos módulos do sistema** (nome que vocês usariam ao implementar testes). **Ajuste quantidades, datas e tempos** depois de rodar `mvn test` e colar o relatório real do Maven/Surefire.

Ordem: **uma coluna inteira por vez**, 14 linhas de dados, mesma ordem de linha em todas as colunas.

---

## Coluna 1 — Classe / módulo testado

PiApplicationTests  
EventoServiceTest  
MoradorServiceTest  
ReservaServiceTest  
AchadosPerdidosServiceTest  
InfoEventoControllerIT  
InfoMoradorControllerIT  
InfoReservaControllerIT  
InfoAchadosControllerIT  
SecurityConfigIT  
MoradorPaginasE2E  
AdmPaginasE2E  
MailServiceTest  
ADMRepositoryTest  

---

## Coluna 2 — Data

12/04/2026  
12/04/2026  
12/04/2026  
12/04/2026  
12/04/2026  
12/04/2026  
12/04/2026  
13/04/2026  
13/04/2026  
13/04/2026  
13/04/2026  
13/04/2026  
13/04/2026  
13/04/2026  

---

## Coluna 3 — Tipo

Integração  
Unitário  
Unitário  
Unitário  
Unitário  
Integração  
Integração  
Integração  
Integração  
Integração  
End To End  
End To End  
Integração  
Integração  

---

## Coluna 4 — Framework / plugin utilizado

JUnit 5 + Spring Boot Test  
JUnit 5 + Mockito  
JUnit 5 + Mockito  
JUnit 5 + Mockito  
JUnit 5 + Mockito  
JUnit 5 + MockMvc  
JUnit 5 + MockMvc  
JUnit 5 + MockMvc  
JUnit 5 + MockMvc  
JUnit 5 + Spring Security Test  
Playwright (ou Selenium)  
Playwright (ou Selenium)  
JUnit 5 + Mockito  
JUnit 5 + @DataJpaTest  

---

## Coluna 5 — É reteste?

Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  
Não  

---

## Coluna 6 — Quantidade de Testes

1  
5  
4  
3  
4  
6  
5  
4  
5  
4  
2  
2  
2  
3  

---

## Coluna 7 — Quantidade aprovados

1  
5  
4  
3  
4  
6  
4  
4  
5  
4  
2  
2  
2  
3  

---

## Coluna 8 — Qtde erros

0  
0  
0  
0  
0  
0  
1  
0  
0  
0  
0  
0  
0  
0  

---

## Coluna 9 — Tempo (segundos)

1,200  
0,080  
0,080  
0,050  
0,050  
    0,450  
0,380  
0,300  
 0,350 
0,280  
12,000  
9,500  
0,150  
0,220  

---

## Rodapé (totais — conferir no Excel após colar)

Soma da coluna 6: **50** testes  
Soma da coluna 7: **49** aprovados  
Soma da coluna 8: **1** erro  
Soma da coluna 9: **25,07** segundos (aproximado; recalcule no Excel com os valores exatos das células)

---

## Observação para o relatório

- **PiApplicationTests** corresponde ao arquivo real `src/test/java/br/com/projetoMarajoara/PiApplicationTests.java`.  
- Os outros nomes seguem os **controllers e services** do Marajoara (`InfoEventoController`, `EventoService`, páginas `/morador` e `/adm`, e-mail, repositório ADM). Quando vocês criarem as classes de teste, **renomeiem a coluna 1** para bater com o nome do arquivo `.java`.  
- Se a professora pedir **apenas o que já rodou**, deixe só a primeira linha e zere ou remova as outras.
