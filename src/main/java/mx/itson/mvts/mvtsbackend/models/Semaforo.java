/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.models;

import jakarta.persistence.*;

/**
 *
 * @author vagui
 */
@Entity
@Table(name = "semaforos")
public class Semaforo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitud;
    private Double longitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 15, nullable = false)
    private EstadoSemaforo estado = EstadoSemaforo.VERDE;

    // Constructores vacíos son requeridos por JPA
    public Semaforo() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public EstadoSemaforo getEstado() { return estado; }
    public void setEstado(EstadoSemaforo estado) { this.estado = estado; }
}
