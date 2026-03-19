# PI Marajoara – Instruções para rodar o projeto

## Análise do projeto

Este é um **sistema web de condomínio (Condomínio Marajoara)** desenvolvido com:

- **Backend:** Java 17 + Spring Boot 3.4.5  
- **Banco de dados:** MySQL  
- **Frontend:** Thymeleaf + HTML/CSS/JS  
- **Segurança:** Spring Security (login por e-mail/senha, perfis: ADM, FUNCIONÁRIO, MORADOR)  
- **E-mail:** Spring Mail (Gmail SMTP) para recuperação de senha e notificações  

**Funcionalidades principais:**  
Login/cadastro de moradores, recuperação de senha, área do morador (eventos, achados e perdidos, aluguel de áreas, reclamações, perfil), área administrativa (gestão de funcionários, eventos, achados, reclamações, configurações).

---

## Pré-requisitos

1. **Java 17** (JDK 17) ou superior instalado.  
   - Verifique: `java -version`

2. **Maven** (ou use o wrapper do projeto: `mvnw` / `mvnw.cmd`).  
   - Verifique: `mvn -version` (se tiver Maven instalado)

3. **MySQL** (5.7+ ou 8.x) instalado e em execução.  
   - Porta padrão: `3306`

---

## Passo 1: Configurar o MySQL (só se for usar MySQL)

Por padrão o projeto roda com **H2** (em memória), sem MySQL. Para usar **MySQL** com banco `marajoara` e usuário **root** / senha **p@$$w0rd**:

1. Use o script e o guia que estão no projeto:
   - **Script SQL:** `criar_banco_marajoara.sql` — cria o banco e define a senha do root.
   - **Guia completo:** `CONFIGURAR_MYSQL.md` — passo a passo e como ajustar usuário/senha no código.

2. Depois de configurar o MySQL, rode a aplicação **com perfil mysql:**
   ```cmd
   .\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql
   ```

Resumo rápido no MySQL (linha de comando ou Workbench):
   ```sql
   CREATE DATABASE IF NOT EXISTS marajoara CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ALTER USER 'root'@'localhost' IDENTIFIED BY 'p@$$w0rd';
   GRANT ALL PRIVILEGES ON marajoara.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

Se quiser usar **outro usuário ou outra senha**, edite o arquivo:
   `src/main/java/br/com/projetoMarajoara/DataConfiguration.java`
   e altere `setUsername(...)` e `setPassword(...)` no método `dataSource()`.

---

## Passo 2: (Opcional) Configurar e-mail

O sistema usa Gmail para envio de e-mails (recuperação de senha, etc.).  
Configuração atual em `src/main/resources/application.properties`:

- `spring.mail.username=enzodanner@gmail.com`
- `spring.mail.password=...`

Para usar sua conta:

1. Ative "Acesso a app menos seguro" ou use **Senha de app** (recomendado) na conta Google.
2. Substitua `spring.mail.username` e `spring.mail.password` no `application.properties`.  
Se não configurar, o resto do sistema funciona; apenas o envio de e-mail pode falhar.

---

## Passo 3: Compilar e rodar a aplicação

Abra um terminal na pasta raiz do projeto (`PI_Marajoara_Final-main`).

### Opção A: Usando o Maven Wrapper (recomendado)

**Windows (PowerShell ou CMD):**
```cmd
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**
```bash
./mvnw spring-boot:run
```

### Opção B: Usando Maven instalado

```bash
mvn clean spring-boot:run
```

### Opção C: Gerar JAR e executar

```bash
mvn clean package -DskipTests
java -jar target/PI-0.0.1-SNAPSHOT.jar
```

---

## Passo 4: Acessar a aplicação

1. Aguarde a mensagem do Spring Boot indicando que a aplicação subiu (por exemplo, "Started PiApplication").

2. No navegador, acesse:
   ```
   http://localhost:8080
   ```

3. Você verá a **tela de login** do Condomínio Marajoara.  
   - Para testar como **morador**, use "Cadastre-se" ou um usuário já cadastrado (e-mail + senha).  
   - Contas de **ADM** ou **FUNCIONÁRIO** precisam existir no banco (criadas pela aplicação ou via dados iniciais/script).

**Observação:** Com `generateDdl=true` na configuração JPA, o Hibernate cria/atualiza as tabelas na primeira execução. Não é obrigatório rodar scripts SQL manualmente para a estrutura básica.

---

## Resumo dos comandos (rápido)

```
1. MySQL: criar banco "marajoara" e usuário root com senha p@$$w0rd (ou ajustar DataConfiguration.java).
2. Na pasta do projeto: .\mvnw.cmd spring-boot:run   (Windows)
3. Abrir: http://localhost:8080
```

---

## Estrutura principal do código

- **Entrada:** `PiApplication.java` (classe principal Spring Boot).
- **Configuração do banco:** `DataConfiguration.java` (DataSource + JPA para MySQL).
- **Segurança:** `Config/SecurityConfig.java` (regras de acesso por URL e perfis).
- **Controllers:** `Controller/PageIndexController.java` (login), `PageMoradorController`, `PageADMController`, etc.
- **Telas:** `src/main/resources/templates/` (login, páginas do morador e da ADM).
- **Estáticos:** `Style/`, `Scripts/`, `Images/` em `src/main/resources/static/` (referenciados nas templates).

Se aparecer algum erro ao rodar (porta em uso, MySQL inacessível ou falha de compilação), envie a mensagem de erro para ajustar os passos.
