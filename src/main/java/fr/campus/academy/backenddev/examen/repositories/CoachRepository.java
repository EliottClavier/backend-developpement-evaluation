package fr.campus.academy.backenddev.examen.repositories;
import fr.campus.academy.backenddev.examen.models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
