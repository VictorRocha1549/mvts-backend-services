package mx.itson.mvts.mvtsbackend.repository.sql;

import mx.itson.mvts.mvtsbackend.models.Semaforo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemaforoRepository extends JpaRepository<Semaforo, Long> {
    // Aquí recuperamos tu repositorio original de semáforos
}