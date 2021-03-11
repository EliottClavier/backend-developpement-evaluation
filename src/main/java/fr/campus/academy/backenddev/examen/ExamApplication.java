package fr.campus.academy.backenddev.examen;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Team;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import fr.campus.academy.backenddev.examen.repositories.TeamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) { SpringApplication.run(ExamApplication.class, args); }

	ExamApplication(PlayerRepository playerRepository, CoachRepository coachRepository, TeamRepository teamRepository) {
		Player player = new Player();
		player.setName("Test");
		playerRepository.save(player);

		Coach coach = new Coach();
		coach.setName("Antoine Kombouaré");

		Team team = new Team();
		team.setName("FC Nantes");

		coach.setTeam(team);
		coachRepository.save(coach);
	}

}
