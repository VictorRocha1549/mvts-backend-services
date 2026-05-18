/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.repository.mongo;

import mx.itson.mvts.mvtsbackend.models.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vagui
 */

/**
 * Esta interfaz maneja toda la comunicación con la colección "rutas_historicas"
 * en tu base de datos de MongoDB Atlas.
 */

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
    // Al heredar de MongoRepository, Spring Boot nos regala automáticamente métodos 
    // como save(), findAll(), findById(), etc. 
}
