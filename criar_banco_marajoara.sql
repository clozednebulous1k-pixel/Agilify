-- ============================================================
-- Script para configurar o MySQL para o projeto PI Marajoara
-- Banco: marajoara | Usuário: root | Senha: p@$$w0rd
-- ============================================================
-- Como usar:
-- 1. Abra o MySQL (linha de comando ou MySQL Workbench).
-- 2. Conecte como root (ou um usuário com permissão de criar bancos).
-- 3. Execute este script inteiro.
-- ============================================================

-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS marajoara
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Definir a senha do usuário root para: p@$$w0rd
-- (MySQL 5.7+ / 8.x)
ALTER USER 'root'@'localhost' IDENTIFIED BY 'p@$$w0rd';

-- Garantir que root tenha todos os privilégios no banco marajoara
GRANT ALL PRIVILEGES ON marajoara.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

-- Confirme com: USE marajoara; SHOW TABLES;
-- As tabelas serão criadas automaticamente pelo Hibernate na primeira execução da aplicação.
