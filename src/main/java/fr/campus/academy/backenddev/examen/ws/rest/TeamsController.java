package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Team;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import fr.campus.academy.backenddev.examen.repositories.TeamRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "teams")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<Team> teamList = this.teamRepository.findAll();
        return ResponseEntity.ok(
                teamList
                        .stream()
                        .map(team -> {
                            List<Long> players = team.getPlayers().stream().map(Player::getId).collect(Collectors.toList());
                            Long coachID = team.getCoach() != null ? team.getCoach().getId() : null;
                            return new TeamDTO(team.getId(),team.getName(), coachID, players);
                        })
                        .collect(Collectors.toList())
        );
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDTO> createTeams(@RequestBody TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getTeamName());
        Coach coach;
        if (teamDTO.getCoach() != null) {
            try {
                coach = this.coachRepository.getOne(teamDTO.getCoach());
                team.setCoach(coach);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            team.setCoach(null);
        }
        List<Player> players;
        if (teamDTO.getPlayers() != null) {
            try {
                players = teamDTO.getPlayers().stream().map(this.playerRepository::getOne).collect(Collectors.toList());
                team.setPlayers(players);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            team.setPlayers(null);
        }
        Team createdTeam = this.teamRepository.save(team);
        Long coachID = createdTeam.getCoach() != null ? createdTeam.getCoach().getId() : null;
        return ResponseEntity.ok(new TeamDTO(createdTeam.getId(), createdTeam.getName(), coachID, createdTeam.getPlayers().stream().map(Player::getId).collect(Collectors.toList())));
    }

    @DeleteMapping(path = "{id}")
    public void deleteTeam(@PathVariable Long id) {
        this.teamRepository.deleteById(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team team;
        try {
            team = this.teamRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        team.setName(teamDTO.getTeamName());
        Coach coach;
        if (teamDTO.getCoach() != null) {
            try {
                coach = this.coachRepository.getOne(teamDTO.getCoach());
                team.setCoach(coach);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        List<Player> players;
        if (teamDTO.getPlayers() != null) {
            try {
                players = this.playerRepository.findAllById(teamDTO.getPlayers());
                // teamDTO.getPlayers().stream().map(this.playerRepository::getOne).collect(Collectors.toList())
                team.setPlayers(players);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Team updatedTeam = this.teamRepository.save(team);
        Long coachID = updatedTeam.getCoach() != null ? updatedTeam.getCoach().getId() : null;
        return ResponseEntity.ok(new TeamDTO(updatedTeam.getId(), updatedTeam.getName(), coachID, updatedTeam.getPlayers().stream().map(Player::getId).collect(Collectors.toList())));
    }

}
