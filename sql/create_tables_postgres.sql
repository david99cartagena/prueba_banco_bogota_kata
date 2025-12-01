DROP TABLE IF EXISTS movimientos_clientes;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
  cliente_id      INTEGER PRIMARY KEY,
  nombre          VARCHAR(100) NOT NULL,
  ciudad          VARCHAR(50),
  fecha_registro  DATE
);

CREATE TABLE movimientos_clientes (
  id_transaccion    INTEGER PRIMARY KEY,
  cliente_id        INTEGER NOT NULL,
  fecha_transaccion DATE NOT NULL,
  monto             NUMERIC(18,2) NOT NULL CHECK (monto >= 0),
  tipo              VARCHAR(50),
  nombre_cliente    VARCHAR(100),
  ciudad_cliente    VARCHAR(50),
  fecha_registro    DATE,
  CONSTRAINT fk_movimientos_cliente
      FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);
