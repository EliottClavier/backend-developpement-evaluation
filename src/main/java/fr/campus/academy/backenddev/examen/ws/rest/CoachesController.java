package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.repositories.CoachRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.CoachDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "coaches")
@RequiredArgsConstructor
public class CoachesController {

    private final CoachRepository coachRepository;

//    @GetMapping
//    public ResponseEntity<List<CoachDTO>> getAllCoaches() {
//        List<Coach> coachList = this.coachRepository.findAll();
//        return ResponseEntity.ok(
//                coachList
//                        .stream()
//                        .map(coach -> {
//                            return new CoachDTO(coach.getId(), coach.getName());
//                        })
//                        .collect(Collectors.toList())
//        );
//    }

}
