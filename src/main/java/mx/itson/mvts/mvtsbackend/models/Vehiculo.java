/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/**
 *
 * @author vagui
 */

/**
 * Entidad que representa la telemetría GPS de un camión minero.
 * La anotación @Document le indica a Spring Boot que debe guardar 
 * estos objetos en una colección de MongoDB llamada "vehiculos".
 */

@Document(collection = "vehiculos")
public class Vehiculo {
    
    @Id
    private String id;

    @Field("vehicle_id")
    private String vehicle_id;
    
    private Double latitude;
    private Double longitude;
    private String status;
    private String timestamp;

    // --- NUEVOS CAMPOS DEL SIMULADOR INTELIGENTE ---
    private Double speed;
    private Double weight;
    private String driver;
    private String material;

    // --- NUEVO CAMPO PARA ESCALABILIDAD DE SEMÁFOROS ---
    private Integer semaforo_id;

    // Constructores
    public Vehiculo() {}

    // --- GETTERS Y SETTERS ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(String vehicle_id) { this.vehicle_id = vehicle_id; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getDriver() { return driver; }
    public void setDriver(String driver) { this.driver = driver; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    
    public Integer getSemaforo_id() { return semaforo_id; }
    public void setSemaforo_id(Integer semaforo_id) { this.semaforo_id = semaforo_id; }
}