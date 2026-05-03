@echo off
cd /d "%~dp0"
echo Iniciando PI Marajoara com banco local persistente (H2 arquivo)...
if exist "%~dp0rodar-local.mail.bat" (
  echo Carregando SMTP de rodar-local.mail.bat ...
  call "%~dp0rodar-local.mail.bat"
) else (
  echo Opcional: crie rodar-local.mail.bat com SET SPRING_MAIL_USERNAME e SET SPRING_MAIL_PASSWORD ^(Senha de app^).
)
call mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
pause
