package fr.campus.academy.backenddev.examen.repositories;
import fr.campus.academy.backenddev.examen.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
