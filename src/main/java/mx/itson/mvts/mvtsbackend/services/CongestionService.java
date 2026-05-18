package mx.itson.mvts.mvtsbackend.services;

import mx.itson.mvts.mvtsbackend.models.Vehiculo;
import mx.itson.mvts.mvtsbackend.models.Congestion;
import mx.itson.mvts.mvtsbackend.repository.sql.CongestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CongestionService {

    @Autowired
    private MongoTemplate mongoTemplate; // Sigue usándose para leer la telemetría rápida de los camiones

    @Autowired
    private CongestionRepository congestionRepository; // Usado para guardar los reportes en MySQL

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Map<Integer, Boolean> alertasPorSemaforo = new ConcurrentHashMap<>();

    @PostConstruct
    public void limpiarHistorial() {
        mongoTemplate.dropCollection(Vehiculo.class);
        System.out.println("Stream 🧹 Telemetría en tiempo real reiniciada en MongoDB.");
    }

    @Scheduled(fixedRate = 5000)
    public void detectarCongestion() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is("detenido_por_semaforo"));
        
        // Leemos de MongoDB el estado actual de los camiones en movimiento/detenidos
        List<Vehiculo> detenidos = mongoTemplate.find(query, Vehiculo.class);

        Map<Integer, Set<String>> camionesPorSemaforo = new HashMap<>();

        for (Vehiculo v : detenidos) {
            Integer semId = v.getSemaforo_id();
            if (semId != null) {
                camionesPorSemaforo.putIfAbsent(semId, new HashSet<>());
                camionesPorSemaforo.get(semId).add(v.getVehicle_id());
            }
        }

        for (Map.Entry<Integer, Set<String>> entry : camionesPorSemaforo.entrySet()) {
            Integer idSemaforo = entry.getKey();
            int cantidad = entry.getValue().size();

            if (cantidad >= 3) {
                if (!alertasPorSemaforo.getOrDefault(idSemaforo, false)) {
                    // 1. Enviamos alerta en tiempo real al mapa por WebSockets
                    String mensaje = "¡ALERTA! Congestión detectada en Semáforo #" + idSemaforo + ". " + cantidad + " unidades detenidas.";
                    messagingTemplate.convertAndSend("/topic/alertas", mensaje);
                    System.out.println("🚨 Alerta enviada a React para Semáforo #" + idSemaforo);
                    
                    // 2. ¡GUARDAMOS EN MYSQL PARA EL REPORTE GERENCIAL!
                    Congestion reporteHistorial = new Congestion(idSemaforo, cantidad, LocalDateTime.now());
                    congestionRepository.save(reporteHistorial); 
                    System.out.println("📝 Bitácora de congestión archivada con éxito en MySQL.");
                    
                    alertasPorSemaforo.put(idSemaforo, true);
                }
            }
        }

        for (Integer idSemaforo : alertasPorSemaforo.keySet()) {
            boolean estabaCongestionado = alertasPorSemaforo.get(idSemaforo);
            int cantidadActual = camionesPorSemaforo.containsKey(idSemaforo) ? camionesPorSemaforo.get(idSemaforo).size() : 0;

            if (estabaCongestionado && cantidadActual < 3) {
                System.out.println("✅ Tráfico liberado en Semáforo #" + idSemaforo + ". Reseteando banderas.");
                alertasPorSemaforo.put(idSemaforo, false);
            }
        }
    }
}