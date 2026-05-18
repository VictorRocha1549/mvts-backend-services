package mx.itson.mvts.mvtsbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Le decimos a Java: "En la base de datos, esto se llama vehicle_id"
    @Column(name = "vehicle_id") 
    private String vehicleId;

    private String conductor;
    private String material;

    // Le decimos a Java: "En la base de datos, esto se llama toneladas"
    @Column(name = "toneladas")
    private Double peso;

    // Le decimos a Java: "En la base de datos, esto se llama fecha_llegada"
    @Column(name = "fecha_llegada")
    private LocalDateTime fechaHora;

    public Entrega() {}

    public Entrega(String vehicleId, String conductor, String material, Double peso, LocalDateTime fechaHora) {
        this.vehicleId = vehicleId;
        this.conductor = conductor;
        this.material = material;
        this.peso = peso;
        this.fechaHora = fechaHora;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getConductor() { return conductor; }
    public void setConductor(String conductor) { this.conductor = conductor; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}