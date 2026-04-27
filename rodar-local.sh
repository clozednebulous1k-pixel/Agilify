#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
echo "Iniciando PI Marajoara com banco local persistente (H2 arquivo)..."
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
