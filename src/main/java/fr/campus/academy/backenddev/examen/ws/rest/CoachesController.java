package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.CoachDTO;
import fr.campus.academy.backenddev.examen.ws.rest.dto.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "coaches")
@RequiredArgsConstructor
public class CoachesController {

    private final CoachRepository coachRepository;

    @GetMapping
    public ResponseEntity<List<CoachDTO>> getAllCoaches() {
        List<Coach> coachList = this.coachRepository.findAll();
        return ResponseEntity.ok(
                coachList
                        .stream()
                        .map(coach -> {
                            return new CoachDTO(coach.getId(), coach.getName(), coach.getTeam());
                        })
                        .collect(Collectors.toList())
        );
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CoachDTO> createPowers(@RequestBody CoachDTO coachDTO) {
        Coach coach = new Coach();
        coach.setName(coachDTO.getName());
        coach.setTeam(coachDTO.getTeam());
        // On sauvegarde le nouvel objet
        Coach createdPower = this.powerRepository.save(power);
        // On retourne l'objet crée
        return ResponseEntity.ok(new PowerDTO(createdPower.getId(), createdPower.getName(), createdPower.getDescription()));
    }

}
