/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata.middle.data.engineer.repository;

import com.mycompany.kata.middle.data.engineer.model.Movimiento;
import java.sql.*;
import java.util.*;

/**
 *
 * @author DavidPC
 */
public class MovimientoRepository {

    public List<Movimiento> findAll() throws Exception {
        List<Movimiento> lista = new ArrayList<>();

        try (Connection conn = Database.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movimientos_clientes ORDER BY id_transaccion DESC");
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setIdTransaccion(rs.getInt("id_transaccion"));
                m.setClienteId(rs.getInt("cliente_id"));
                m.setFecha_transaccion(rs.getString("fecha_transaccion"));
                m.setMonto(rs.getDouble("monto"));
                m.setTipo(rs.getString("tipo"));
                m.setNombreCliente(rs.getString("nombre_cliente"));
                m.setCiudadCliente(rs.getString("ciudad_cliente"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error consultando movimientos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
