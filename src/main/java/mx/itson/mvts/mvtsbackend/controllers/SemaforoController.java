package mx.itson.mvts.mvtsbackend.controllers;

import mx.itson.mvts.mvtsbackend.models.Semaforo;
import mx.itson.mvts.mvtsbackend.repository.sql.SemaforoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/semaforos")
@CrossOrigin(origins = "*") // ¡Magia anti-CORS para que React no llore!
public class SemaforoController {

    @Autowired
    private SemaforoRepository semaforoRepository;

    // 1. Obtener todos los semáforos (React lo usará para dibujarlos en el mapa)
    @GetMapping
    public List<Semaforo> obtenerTodos() {
        return semaforoRepository.findAll();
    }

    // 2. Crear un semáforo (Para darlos de alta la primera vez con sus coordenadas)
    @PostMapping
    public Semaforo crearSemaforo(@RequestBody Semaforo semaforo) {
        return semaforoRepository.save(semaforo);
    }

    // 3. Cambiar el color del semáforo (React lo usará cuando presiones un botón)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Semaforo> actualizarEstado(@PathVariable Long id, @RequestBody Semaforo nuevoEstado) {
        Optional<Semaforo> semaforoExistente = semaforoRepository.findById(id);

        if (semaforoExistente.isPresent()) {
            Semaforo semaforo = semaforoExistente.get();
            semaforo.setEstado(nuevoEstado.getEstado());
            return ResponseEntity.ok(semaforoRepository.save(semaforo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}