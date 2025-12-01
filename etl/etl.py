from pathlib import Path
import pandas as pd
import psycopg2
from config import PG_CONFIG

print("Iniciando proceso ETL...")

# ---------------------------------------------------------
# 1. Definir rutas de forma segura
# ---------------------------------------------------------
try:
    project_root = Path(__file__).resolve().parents[1]
    data_dir = project_root / "data"

    csv_in = data_dir / "transacciones.csv"
    csv_out = data_dir / "movimientos_clientes.csv"
except Exception as e:
    print(f"ERROR determinando rutas del proyecto: {e}")
    exit(1)

# ---------------------------------------------------------
# 2. Validar archivo CSV de entrada
# ---------------------------------------------------------
if not csv_in.exists():
    print(f"ERROR: No se encontró el archivo CSV de entrada: {csv_in}")
    exit(1)

# ---------------------------------------------------------
# 3. Conexión segura a PostgreSQL
# ---------------------------------------------------------
try:
    conn = psycopg2.connect(**PG_CONFIG)
except Exception as e:
    print("ERROR conectando a PostgreSQL:", e)
    exit(1)

# ---------------------------------------------------------
# 4. Cargar datos de la tabla clientes
# ---------------------------------------------------------
try:
    clientes_df = pd.read_sql("SELECT * FROM clientes", conn)
except Exception as e:
    print("ERROR consultando la tabla 'clientes':", e)
    exit(1)

if clientes_df.empty:
    print("Advertencia: La tabla 'clientes' está vacía. Proceso detenido.")
    exit(1)

# ---------------------------------------------------------
# 5. Cargar CSV crudo
# ---------------------------------------------------------
try:
    trans_df = pd.read_csv(csv_in)
except Exception as e:
    print("ERROR al leer el CSV de transacciones:", e)
    exit(1)

# Validar columnas esperadas
expected_cols = {"id_transaccion", "cliente_id", "fecha", "monto", "tipo"}
if not expected_cols.issubset(trans_df.columns):
    print("ERROR: El CSV NO contiene las columnas requeridas:", expected_cols)
    exit(1)

# ---------------------------------------------------------
# 6. Limpieza de datos
# ---------------------------------------------------------
# Convertir montos
trans_df["monto"] = pd.to_numeric(trans_df["monto"], errors="coerce")

# Eliminar registros incompletos
trans_df = trans_df.dropna(
    subset=["id_transaccion", "cliente_id", "fecha", "monto", "tipo"]
)

# Eliminar montos negativos
trans_df = trans_df[trans_df["monto"] > 0]

# Filtrar por clientes válidos
trans_df = trans_df[trans_df["cliente_id"].isin(clientes_df["cliente_id"])]

if trans_df.empty:
    print("Advertencia: No quedaron transacciones válidas después de la limpieza.")
    exit(0)

# ---------------------------------------------------------
# 7. Enriquecimiento (join)
# ---------------------------------------------------------
try:
    df_final = trans_df.merge(clientes_df, on="cliente_id", how="inner")
    df_final.rename(
        columns={
            "fecha": "fecha_transaccion",
            "nombre": "nombre_cliente",
            "ciudad": "ciudad_cliente",
        },
        inplace=True,
    )
except Exception as e:
    print("ERROR durante el enriquecimiento de datos:", e)
    exit(1)

# ---------------------------------------------------------
# 8. Exportar CSV final (UTF-8 BOM para evitar errores con PostgreSQL)
# ---------------------------------------------------------
try:
    df_final.to_csv(csv_out, index=False, encoding="utf-8-sig")
except Exception as e:
    print("ERROR al escribir el archivo final:", e)
    exit(1)

# ---------------------------------------------------------
# 9. Resultado final
# ---------------------------------------------------------
print(f"ETL COMPLETADO CORRECTAMENTE")
print(f"Archivo generado: {csv_out}")
print(f"Registros válidos generados: {len(df_final)}")

if not df_final.empty:
    print(df_final.to_string(index=False))
