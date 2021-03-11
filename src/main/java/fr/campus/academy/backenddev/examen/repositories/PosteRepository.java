package fr.campus.academy.backenddev.examen.repositories;
import fr.campus.academy.backenddev.examen.models.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosteRepository extends JpaRepository<Poste, Long> {
}
