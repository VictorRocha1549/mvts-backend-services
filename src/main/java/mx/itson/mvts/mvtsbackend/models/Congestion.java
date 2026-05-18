package mx.itson.mvts.mvtsbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_congestiones") // Esto creará la tabla automáticamente en MySQL
public class Congestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer semaforoId;
    private Integer cantidadVehiculos;
    private LocalDateTime fechaHora; // Formato nativo de fecha para SQL

    // Constructor vacío requerido por JPA
    public Congestion() {}

    public Congestion(Integer semaforoId, Integer cantidadVehiculos, LocalDateTime fechaHora) {
        this.semaforoId = semaforoId;
        this.cantidadVehiculos = cantidadVehiculos;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getSemaforoId() { return semaforoId; }
    public void setSemaforoId(Integer semaforoId) { this.semaforoId = semaforoId; }

    public Integer getCantidadVehiculos() { return cantidadVehiculos; }
    public void setCantidadVehiculos(Integer cantidadVehiculos) { this.cantidadVehiculos = cantidadVehiculos; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
} 