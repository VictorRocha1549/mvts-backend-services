/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.mvts.mvtsbackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.itson.mvts.mvtsbackend.models.Vehiculo;
import mx.itson.mvts.mvtsbackend.models.Entrega;
import mx.itson.mvts.mvtsbackend.repository.mongo.VehiculoRepository;
import mx.itson.mvts.mvtsbackend.repository.sql.EntregaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.time.LocalDateTime;

/**
 * Servicio encargado de escuchar la cola de mensajería y procesar los datos.
 */
@Service
public class GpsConsumer {
    
    @Autowired
    private VehiculoRepository mongoRepository;

    @Autowired
    private EntregaRepository mysqlEntregaRepository; // Inyectamos la conexión a MySQL

    @Autowired
    private SimpMessagingTemplate template;

    @RabbitListener(queues = "telemetry.gps")
    public void recibirGps(String mensajeJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Vehiculo vehiculo = mapper.readValue(mensajeJson, Vehiculo.class);
            
            // 1. Seguimos guardando la telemetría en MongoDB
            mongoRepository.save(vehiculo);
            
            // 2. Enviamos el objeto al mapa de React
            template.convertAndSend("/topic/ruta", vehiculo);
            
            // 3. ¡EL INTERCEPTOR GERENCIAL!
            // Si el camión dice que ya llegó, guardamos sus datos de carga en MySQL
            if ("llegada".equals(vehiculo.getStatus())) {
                Entrega nuevaEntrega = new Entrega(
                    vehiculo.getVehicle_id(),
                    vehiculo.getDriver(),
                    vehiculo.getMaterial(),
                    vehiculo.getWeight(),
                    LocalDateTime.now()
                );
                mysqlEntregaRepository.save(nuevaEntrega);
                System.out.println("📦 ¡Carga registrada en MySQL! Unidad: " + vehiculo.getVehicle_id() + " entregó " + vehiculo.getMaterial());
            } else {
                System.out.println("[🚀] Coordenada enviada a WebSockets y MongoDB: " + vehiculo.getVehicle_id());
            }

        } catch (Exception e) {
            System.err.println("[❌] Error: " + e.getMessage());
        }
    }
}
