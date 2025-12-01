/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata.middle.data.engineer.service;

import com.mycompany.kata.middle.data.engineer.model.Movimiento;
import com.mycompany.kata.middle.data.engineer.repository.MovimientoRepository;
import java.util.List;

/**
 *
 * @author DavidPC
 */
public class MovimientosService {

    private MovimientoRepository repo = new MovimientoRepository();

    public List<Movimiento> listarMovimientos() throws Exception {
        return repo.findAll();
    }

}
