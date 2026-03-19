# Configurar MySQL para o PI Marajoara

O projeto pode rodar com **H2** (em memória, sem instalar nada) ou com **MySQL**. Para usar MySQL com usuário **root** e senha **p@$$w0rd**, siga um dos caminhos abaixo.

---

## Opção 1: Criar banco e definir senha do root (recomendado)

### Passo a passo

1. **Abra o MySQL**
   - **Linha de comando:** abra o CMD ou PowerShell e rode:
     ```cmd
     mysql -u root -p
     ```
     (Digite a senha atual do root quando pedir; se não tiver senha, só pressione Enter.)
   - **MySQL Workbench:** conecte no servidor local como root.

2. **Execute o script SQL** que está no projeto:
   - No terminal (fora do `mysql`), na pasta do projeto:
     ```cmd
     mysql -u root -p < criar_banco_marajoara.sql
     ```
   - **Ou** abra o arquivo `criar_banco_marajoara.sql` no MySQL Workbench e execute (Execute → Run).

3. **Conteúdo do script (se quiser copiar e colar no MySQL):**
   ```sql
   CREATE DATABASE IF NOT EXISTS marajoara
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_unicode_ci;

   ALTER USER 'root'@'localhost' IDENTIFIED BY 'p@$$w0rd';
   GRANT ALL PRIVILEGES ON marajoara.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. **Rodar a aplicação com MySQL**
   Na pasta do projeto:
   ```cmd
   .\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql
   ```

---

## Opção 2: Manter sua senha do root e ajustar o código

Se você **não quiser** usar a senha `p@$$w0rd` no MySQL e preferir manter a senha que já usa no root:

1. **Crie só o banco** (no MySQL):
   ```sql
   CREATE DATABASE IF NOT EXISTS marajoara
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_unicode_ci;
   ```

2. **Edite o `DataConfiguration.java`** e coloque a **sua** senha do root:
   - Arquivo: `src/main/java/br/com/projetoMarajoara/DataConfiguration.java`
   - No método `dataSource()`, troque a linha:
     ```java
     dataSource.setPassword("p@$$w0rd");
     ```
     por algo como:
     ```java
     dataSource.setPassword("SUA_SENHA_AQUI");
     ```

3. **Rode com perfil mysql:**
   ```cmd
   .\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql
   ```

---

## Resumo

| O que você quer              | O que fazer |
|-----------------------------|-------------|
| Usar root com senha `p@$$w0rd` | Rodar o script `criar_banco_marajoara.sql` e depois: `.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql` |
| Usar root com outra senha   | Criar o banco `marajoara`, alterar `setPassword("...")` no `DataConfiguration.java` e rodar com `-Dspring-boot.run.profiles=mysql` |
| Não usar MySQL (só testar)  | Rode só `.\mvnw.cmd spring-boot:run` (usa H2 em memória) |

Depois de configurar, acesse: **http://localhost:8080**
