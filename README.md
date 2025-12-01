# Prueba Banco Bogotá Kata

Demostrar el flujo de un proceso **ETL** que extrae datos de una base de datos **PostgreSQL**, realiza transformaciones y limpieza de datos, y finalmente exporta los datos procesados a un archivo **.CSV**. Además se incluye un módulo de visualización en **Java** para procesar y presentar los resultados generados por el proceso ETL.

## Funciones

- **ETL (Extract, Transform, Load):** Extrae los datos de la tabla `clientes` desde PostgreSQL, limpia los datos de las transacciones y exporta los resultados a un archivo CSV.
- **Conexión con PostgreSQL:** Se utiliza `psycopg2` para conectar con la base de datos y realizar operaciones SQL.
- **Procesamiento de datos con Python:** Se usa `pandas` para la transformación de datos, como convertir montos a numérico, eliminar registros nulos, y enriquecer datos.
- **Visualización Java:** Un módulo en Java que muestra los datos procesados desde el CSV generado en el proceso ETL.

## 🖼️ Imagenes de la Aplicacion

- **Estructura bases de datos**
  ![base-de-datos](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_3.1.png)

- **Proceso de ETL**
  ![proceso-etl](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_4.png)

- **Proceso de Visualizacion Java**
  ![visualizacion-java](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_5.png)

  ![resultado-archivo-csv](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_7.png)

## ⚙️ Tecnologías Utilizadas

- **Python 3.12**
- _python-dotenv 1.2.1_
- _psycopg2 2.9.11_
- _pandas 2.3.2_
- **Java 17.0.12**
- _java-dotenv 5.2.2_
- _postgresql 42.7.3_
- **PostgreSQL 13.21**
- **DBeaver 25 ( Opcional )**
- **NetBeans IDE 23**

## 📁 Estructura del Proyecto

```
/kata-middle-data-engineer
├── .env
├── .env.example
├── .gitignore
├── pom.xml
├── README.md

├── data
│   ├── movimientos_clientes.csv
│   └── transacciones.csv

├── etl
│   ├── config.py
│   ├── etl.py
│   ├── requirements.txt
│   ├── run_etl.sh
│   └── __pycache__/
│       └── config.cpython-312.pyc

├── sql
│   ├── create_tables_postgres.sql
│   └── inserts_clientes.sql

├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── mycompany
│   │               └── kata
│   │                   └── middle
│   │                       └── data
│   │                           └── engineer
│   │                               ├── controller
│   │                               │   └── MovimientosController.java
│   │                               ├── KataMiddleDataEngineer.java
│   │                               ├── model
│   │                               │   └── Movimiento.java
│   │                               ├── repository
│   │                               │   ├── Database.java
│   │                               │   └── MovimientoRepository.java
│   │                               └── service
│   │                                   └── MovimientosService.java
│
│   └── test
│       └── java
```

## 🛠️ Pasos para instalar y correr el proyecto

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/david99cartagena/prueba_banco_bogota_kata.git
   ```

   ```bash
   cd prueba_banco_bogota_kata
   ```

2. **Copiar el archivo de entorno `.env.example` cambiarlo a `.env`:**

   _Ejemplo de .env_

   ```env
   PG_HOST=example_host
   PG_DB=example_database
   PG_USER=example_user
   PG_PASSWORD=example_password
   ```

3. **Instalar las dependencias:**

   **python**

   ```bash
   pip install -r etl/requirements.txt
   ```

   o

   ```bash
   pip install python-dotenv psycopg2 pandas
   ```

   **Java**
   Version de maven

   ```bash
   mvn -v
   ```

   Buscar el archivo pom.xml

   ```bash
   pom.xml
   ```

   Entra al directorio del proyecto

   ```bash
   cd ruta/del/proyecto
   ```

   - Ejemplo

   ```bash
   cd C:\Users\Tu-Usuario\workspace\MiProyecto
   ```

   Instalar dependencias del proyecto

   ```bash
   mvn install
   ```

4. **Uso de Script SQL ( PostgreSQL )**
   ![base-de-datos](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_1.png)

   ![base-de-datos](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_2.png)

   ![base-de-datos](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_3.1.png)

5. **Proceso de ETL**

- se usa git bash para tener una interface entre para ejecutar algunos comandos de **Linux** en windows.
- se accede a la carpeta **etl**.
- se usa _chmod +x para dar credenciales o permisos especiales_.
- se ejecuta el script bash.
- se valida la generacion del archivo .csv
  ![proceso-etl](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_4.png)

6. **Proceso de Visualizacion Java**

   - se ejecuta proyecto con **Netbeans**
   - se carga por terminal o consola la visualizacion de la data relacionada.
     ![visualizacion-java](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_5.png)

     ![visualizacion-java](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_6.png)

     ![resultado-archivo-csv](https://raw.githubusercontent.com/david99cartagena/prueba_banco_bogota_kata/refs/heads/main/media/Screenshot_7.png)
