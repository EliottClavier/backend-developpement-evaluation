package fr.campus.academy.backenddev.examen.ws.rest.dto;

import fr.campus.academy.backenddev.examen.models.Coach;
import fr.campus.academy.backenddev.examen.models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {
    private Long id;
    private String name;
    private TeamDTO team;
    private List<CoachDTO> assistants;
}
