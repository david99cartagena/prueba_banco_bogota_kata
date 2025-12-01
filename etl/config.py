from dotenv import load_dotenv
import os
from pathlib import Path

# Carga .env desde la raíz del proyecto
project_root = Path(__file__).resolve().parents[1]
env_path = project_root / ".env"
load_dotenv(dotenv_path=env_path)

PG_CONFIG = {
    "host": os.getenv("PG_HOST"),
    "database": os.getenv("PG_DB"),
    "user": os.getenv("PG_USER"),
    "password": os.getenv("PG_PASSWORD"),
}
