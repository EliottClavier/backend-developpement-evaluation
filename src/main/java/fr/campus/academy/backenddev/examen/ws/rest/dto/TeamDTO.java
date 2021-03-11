package fr.campus.academy.backenddev.examen.ws.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String teamName;
    private Long coach;
    private List<Long> players;
}
