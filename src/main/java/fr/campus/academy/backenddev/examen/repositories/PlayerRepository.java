package fr.campus.academy.backenddev.examen.repositories;
import fr.campus.academy.backenddev.examen.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
