@echo off
cd /d "%~dp0"
echo Iniciando PI Marajoara com banco local persistente (H2 arquivo)...
call mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
pause
