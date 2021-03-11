package fr.campus.academy.backenddev.examen.ws.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {
    private Long id;
    private String label;
    private List<Long> players;
}
