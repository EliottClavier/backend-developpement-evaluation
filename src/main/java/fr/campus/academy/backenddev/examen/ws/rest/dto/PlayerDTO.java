package fr.campus.academy.backenddev.examen.ws.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private Integer number;
    private Long team;
    private List<Long> positions;
}
