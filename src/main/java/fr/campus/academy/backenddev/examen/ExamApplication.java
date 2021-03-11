package fr.campus.academy.backenddev.examen;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Position;
import fr.campus.academy.backenddev.examen.models.Team;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import fr.campus.academy.backenddev.examen.repositories.TeamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) { SpringApplication.run(ExamApplication.class, args); }

	ExamApplication(PlayerRepository playerRepository, CoachRepository coachRepository, TeamRepository teamRepository) {
//		Coach coach = new Coach();
//		coach.setName("Jorge Sampaoli");
//
//		Team team = new Team();
//		team.setName("Olympique de Marseille");
//
//		Player player = new Player();
//		player.setName("Mickaël Cuisance");
//		player.setTeam(team);
//		player.setNumber(17);
//
//		Position position = new Position();
//		position.setLabel("Milieu");
//		List<Position> positionsList = new ArrayList<>();
//		positionsList.add(position);
//
//		player.setPositions(positionsList);
//		List<Player> playersList = new ArrayList<>();
//		playersList.add(player);
//		team.setPlayers(playersList);
//
//		coach.setTeam(team);
//		coachRepository.save(coach);
	}

}
