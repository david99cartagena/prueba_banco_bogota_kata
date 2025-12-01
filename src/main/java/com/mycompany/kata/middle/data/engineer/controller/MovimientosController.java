/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata.middle.data.engineer.controller;

import com.mycompany.kata.middle.data.engineer.model.Movimiento;
import com.mycompany.kata.middle.data.engineer.service.MovimientosService;
import java.util.List;

/**
 *
 * @author DavidPC
 */
public class MovimientosController {

    private MovimientosService service = new MovimientosService();

    public void listar() throws Exception {
        List<Movimiento> lista = service.listarMovimientos();
        lista.forEach(m -> {
            System.out.println(
                    m.getIdTransaccion() + " | "
                    + m.getNombreCliente() + " | "
                    + m.getMonto()
            );
        });
    }
}
