package fr.campus.academy.backenddev.examen.repositories;
import fr.campus.academy.backenddev.examen.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
