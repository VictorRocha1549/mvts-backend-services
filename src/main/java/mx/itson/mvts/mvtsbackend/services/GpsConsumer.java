/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.itson.mvts.mvtsbackend.models.Vehiculo;
import mx.itson.mvts.mvtsbackend.repositories.VehiculoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author vagui
 */

/**
 * Servicio encargado de escuchar la cola de mensajería y procesar los datos.
 */
@Service
public class GpsConsumer {
    
    @Autowired
    private VehiculoRepository repository;

    // Esta es la herramienta mágica para enviar mensajes por WebSocket
    @Autowired
    private SimpMessagingTemplate template;

    @RabbitListener(queues = "telemetry.gps")
    public void recibirGps(String mensajeJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Vehiculo vehiculo = mapper.readValue(mensajeJson, Vehiculo.class);
            
            // 1. Seguimos guardando en la nube (para el historial)
            repository.save(vehiculo);
            
            // 2. ¡EL CAMBIO MAESTRO!: Enviamos el objeto al canal "/topic/ruta"
            // Omar estará escuchando este canal específico.
            template.convertAndSend("/topic/ruta", vehiculo);
            
            System.out.println("[🚀] Coordenada enviada a WebSockets y MongoDB: " + vehiculo.getVehicle_id());

        } catch (Exception e) {
            System.err.println("[❌] Error: " + e.getMessage());
        }
    }
    
}
