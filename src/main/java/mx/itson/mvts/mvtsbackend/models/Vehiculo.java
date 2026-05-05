/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author vagui
 */

/**
 * Entidad que representa la telemetría GPS de un camión minero.
 * La anotación @Document le indica a Spring Boot que debe guardar 
 * estos objetos en una colección de MongoDB llamada "rutas_historicas".
 */

@Data
@Document(collection = "rutas_historicas")
public class Vehiculo {
    
    @Id
    private String id; // Este ID lo generará MongoDB automáticamente (ej. 60f7a9b...)
    
    // Estos nombres son iguales a las llaves del JSON del acuerdo llegado con Bruno y Omar.
    private String vehicle_id;
    private double latitude;
    private double longitude;
    private String status;
    private String timestamp;
    
}
