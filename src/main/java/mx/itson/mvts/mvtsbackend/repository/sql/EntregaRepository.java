package mx.itson.mvts.mvtsbackend.repository.sql;

import mx.itson.mvts.mvtsbackend.models.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
