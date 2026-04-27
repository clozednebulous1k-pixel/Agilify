# Deploy sem mudar o sistema (Spring + Front atual)

Este projeto continua em Spring Boot + Thymeleaf (front igual).

## 1) Variaveis de ambiente (producao)

Defina no provedor onde o container rodar:

- `SPRING_PROFILES_ACTIVE=prod`
- `SUPABASE_DB_URL=jdbc:postgresql://<host>:5432/postgres?sslmode=require`
- `SUPABASE_DB_USER=postgres.<project-ref>`
- `SUPABASE_DB_PASSWORD=<sua-senha>`
- `SPRING_MAIL_USERNAME=<seu-email>`
- `SPRING_MAIL_PASSWORD=<senha-app-email>`

## 2) Build local

```bash
./mvnw clean package -DskipTests
```

## 3) Rodar local em modo producao

```bash
SPRING_PROFILES_ACTIVE=prod java -jar target/*.jar
```

## 4) Deploy com container

O projeto ja inclui `Dockerfile`, entao basta usar uma plataforma que aceite container Java.

Comando de execucao do container:

```bash
docker build -t marajoara-app .
docker run -p 8080:8080 --env-file .env marajoara-app
```

## Observacao sobre Vercel/Netlify

Vercel e Netlify funcionam melhor para frontend/serverless. Este backend Spring monolitico deve subir em plataforma de container/VM.
