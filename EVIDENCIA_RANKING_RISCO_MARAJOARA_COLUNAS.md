# Ranking de Risco — PI Marajoara (valores por coluna para colar na planilha)

Mesma lógica da planilha modelo: **Total = Probabilidade (num) × Severidade (num) × Relevância (num)**.  
Escala numérica típica: Baixo = 2, Moderado = 3, Alto = 4, Muito Alto = 5.

**9 linhas** de dados (uma linha = uma célula ao colar na coluna do Excel).

---

## Coluna A — Risco

Dois moradores reservam o mesmo horário ou área comum  
Falha no envio de lembretes (e-mail / SMS)  
Interface pouco intuitiva gera erros em reserva ou em formulários  
Tempo de resposta maior que 2 s em horários de pico  
Histórico de reservas ou eventos indisponível  
Vazamento de dados sensíveis (LGPD)  
Indisponibilidade do sistema (meta típica acima de 0,5% no período)  
Erro na integração com e-mail ou outro serviço externo (SMTP, APIs)  
Concorrência com sistemas ou portais de condomínio mais maduros  

---

## Coluna B — Probabilidade (texto)

Alto  
Moderado  
Moderado  
Alto  
Baixo  
Baixo  
Moderado  
Moderado  
Moderado  

---

## Coluna C — Severidade (texto)

Alto  
Moderado  
Moderado  
Alto  
Moderado  
Muito Alto  
Alto  
Alto  
Moderado  

---

## Coluna D — Relevância (texto)

Muito Alto  
Alto  
Moderado  
Muito Alto  
Moderado  
Muito Alto  
Alto  
Alto  
Moderado  

---

## Coluna E — Probabilidade (valor numérico)

4  
3  
3  
4  
2  
2  
3  
3  
3  

---

## Coluna F — Severidade (valor numérico)

4  
3  
3  
4  
3  
5  
4  
4  
3  

---

## Coluna G — Relevância (valor numérico)

5  
4  
3  
5  
3  
5  
4  
4  
3  

---

## Coluna H — Total

80  
36  
27  
80  
18  
50  
48  
48  
27  

---

## Conferência dos totais (P × S × R)

- Linha 1: 4 × 4 × 5 = **80**  
- Linha 2: 3 × 3 × 4 = **36**  
- Linha 3: 3 × 3 × 3 = **27**  
- Linha 4: 4 × 4 × 5 = **80**  
- Linha 5: 2 × 3 × 3 = **18**  
- Linha 6: 2 × 5 × 5 = **50**  
- Linha 7: 3 × 4 × 4 = **48**  
- Linha 8: 3 × 4 × 4 = **48**  
- Linha 9: 3 × 3 × 3 = **27**  

---

*Projeto: Condomínio Marajoara (Spring Boot, reservas, eventos, e-mail, LGPD). Ajuste textos ou notas se a banca pedir outra escala.*
