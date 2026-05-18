package mx.itson.mvts.mvtsbackend.repository.sql;

import mx.itson.mvts.mvtsbackend.models.Congestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongestionRepository extends JpaRepository<Congestion, Long> {
    // Este es el repositorio exclusivo para el historial de congestiones
}