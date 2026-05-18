/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.controllers;

import mx.itson.mvts.mvtsbackend.models.Vehiculo;
import mx.itson.mvts.mvtsbackend.repository.mongo.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *
 * @author vagui
 */

/**
 * Controlador REST que expone los datos de la mina hacia la interfaz web dde React.
 */
@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*") //Permite que cualquier frontend se conecte sin bloqueos de seguridad
public class VehiculoController {
    
    @Autowired
    private VehiculoRepository repository;

    /**
     * Endpoint para obtener todas las posiciones históricas.
     * Omar consumirá esta ruta desde su código usando Axios: 
     * GET http://localhost:8080/api/vehiculos/activos
     */
    @GetMapping("/activos")
    public List<Vehiculo> obtenerTodos() {
        // Ejecuta un "SELECT *" en MongoDB y automáticamente convierte los resultados
        // a una lista JSON perfecta para que React la procese.
        return repository.findAll();
    }
    
}
