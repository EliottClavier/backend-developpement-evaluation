package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Position;
import fr.campus.academy.backenddev.examen.models.Team;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.repositories.TeamRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.CoachDTO;
import fr.campus.academy.backenddev.examen.ws.rest.dto.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "coaches")
@RequiredArgsConstructor
public class CoachesController {

    private final CoachRepository coachRepository;
    private final TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<CoachDTO>> getAllCoaches() {
        List<Coach> coachList = this.coachRepository.findAll();
        return ResponseEntity.ok(
                coachList
                        .stream()
                        .map(coach -> {
                            Long team = coach.getTeam() != null ? coach.getTeam().getId() : null;
                            return new CoachDTO(coach.getId(), coach.getName(), team);
                        })
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CoachDTO> getCoachById(@PathVariable Long id) {
        try {
            Coach coach = this.coachRepository.getOne(id);
            Long teamID = coach.getTeam() != null ? coach.getTeam().getId() : null;
            return ResponseEntity.ok(new CoachDTO(coach.getId(), coach.getName(), teamID));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CoachDTO> createCoaches(@RequestBody CoachDTO coachDTO) {
        Coach coach = new Coach();
        coach.setName(coachDTO.getName());
        Team team;
        if (coachDTO.getTeam() != null) {
            try {
                team = this.teamRepository.getOne(coachDTO.getTeam());
                coach.setTeam(team);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Coach createdCoach = this.coachRepository.save(coach);
        Long teamID = coach.getTeam() != null ? coach.getTeam().getId() : null;
        return ResponseEntity.ok(new CoachDTO(createdCoach.getId(), createdCoach.getName(), teamID));
    }

    @DeleteMapping(path = "{id}")
    public void deleteCoach(@PathVariable Long id) {
        this.coachRepository.deleteById(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CoachDTO> updateCoach(@PathVariable Long id, @RequestBody CoachDTO coachDTO) {
        Coach coach;
        try {
            coach = this.coachRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        coach.setName(coachDTO.getName());
        Team team;
        if (coachDTO.getTeam() != null) {
            try {
                team = this.teamRepository.getOne(coachDTO.getTeam());
                coach.setTeam(team);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Coach updatedCoach = this.coachRepository.save(coach);
        Long teamID = coach.getTeam() != null ? coach.getTeam().getId() : null;
        return ResponseEntity.ok(new CoachDTO(updatedCoach.getId(), updatedCoach.getName(), teamID));
    }
}
