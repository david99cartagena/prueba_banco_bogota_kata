/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.kata.middle.data.engineer;

import com.mycompany.kata.middle.data.engineer.controller.MovimientosController;

/**
 *
 * @author DavidPC
 */
public class KataMiddleDataEngineer {
    public static void main(String[] args) {
        try {
            System.out.println("Cargando movimientos desde PostgreSQL...");
            new MovimientosController().listar();
        } catch (Exception e) {
            System.out.println("No fue posible cargar los movimientos.");
            System.out.println("Detalle técnico: " + e.getMessage());
        }
    }
}