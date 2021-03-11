package fr.campus.academy.backenddev.examen;

import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) { SpringApplication.run(ExamApplication.class, args); }

	ExamApplication(PlayerRepository playerRepository) {
		Player player = new Player();
		player.setName("Test");
		playerRepository.save(player);
	}

}
