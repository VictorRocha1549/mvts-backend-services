/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.controllers;

import mx.itson.mvts.mvtsbackend.models.Congestion;
import mx.itson.mvts.mvtsbackend.models.Entrega;
import mx.itson.mvts.mvtsbackend.repository.sql.CongestionRepository;
import mx.itson.mvts.mvtsbackend.repository.sql.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*") // Permite que React consuma estos datos sin problemas de seguridad
public class ReportesController {

    @Autowired
    private CongestionRepository congestionRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    // 1. Endpoint para el reporte de Congestiones
    // React lo llamará haciendo un GET a: http://localhost:8080/api/reportes/congestiones
    @GetMapping("/congestiones")
    public List<Congestion> obtenerCongestiones() {
        return congestionRepository.findAll();
    }

    // 2. Endpoint para el reporte de Materiales
    // React lo llamará haciendo un GET a: http://localhost:8080/api/reportes/materiales
    @GetMapping("/materiales")
    public List<Entrega> obtenerEntregas() {
        return entregaRepository.findAll();
    }
}