@echo off
cd /d "%~dp0"
echo Iniciando PI Marajoara com MySQL (perfil mysql)...
call mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql
pause
