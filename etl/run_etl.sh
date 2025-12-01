#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

echo "===== Ejecutando proceso ETL ===== $(date)"

echo "1. Extracción y transformación (Python)"
python etl.py

# ================================
#  LEER VARIABLES DEL .env
# ================================
ENV_FILE="../.env"

if [ -f "$ENV_FILE" ]; then
    export $(grep -v '^#' "$ENV_FILE" | xargs)
else
    echo "❌ No se encontró el archivo .env"
    exit 1
fi

echo "2. Carga a PostgreSQL"

# -------------------------------
# Rutas usuales de psql en Windows
# -------------------------------
PSQL_PATH="/c/Program Files/PostgreSQL/13/bin/psql.exe"
ALT_PSQL_PATH="/c/Program Files/PostgreSQL/13/pgAdmin 4/runtime/psql.exe"

if [ ! -f "$PSQL_PATH" ]; then
    PSQL_PATH="$ALT_PSQL_PATH"
fi

if [ ! -f "$PSQL_PATH" ]; then
    echo "No se encontró psql.exe"
    exit 1
fi

CSV="../data/movimientos_clientes.csv"

if [ ! -f "$CSV" ]; then
    echo "No existe el archivo CSV generado."
    exit 1
fi

echo "Cargando datos a PostgreSQL..."

"$PSQL_PATH" \
    -h "$PG_HOST" \
    -U "$PG_USER" \
    -d "$PG_DB" \
    -c "\copy movimientos_clientes FROM '$CSV' CSV HEADER ENCODING 'UTF8';"

echo "Carga completada."
echo "===== Proceso Completado ===== $(date)"
