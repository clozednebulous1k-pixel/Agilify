@echo off
cd /d "%~dp0"
echo Iniciando PI Marajoara (Spring Boot)...
call mvnw.cmd spring-boot:run
pause
