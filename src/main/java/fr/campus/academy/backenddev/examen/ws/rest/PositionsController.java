package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Position;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import fr.campus.academy.backenddev.examen.repositories.PositionRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.PositionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "positions")
@RequiredArgsConstructor
public class PositionsController {

    private final PositionRepository positionRepository;
    private final PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        List<Position> positionList = this.positionRepository.findAll();
        return ResponseEntity.ok(
                positionList
                        .stream()
                        .map(position -> {
                            List<Long> players = position.getPlayers().stream().map(Player::getId).collect(Collectors.toList());
                            return new PositionDTO(position.getId(),position.getLabel(), players);
                        })
                        .collect(Collectors.toList())
        );
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionDTO> createPositions(@RequestBody PositionDTO positionDTO) {
        Position position = new Position();
        position.setLabel(position.getLabel());
        List<Player> players;
        if (positionDTO.getPlayers() != null) {
            try {
                players = positionDTO.getPlayers().stream().map(this.playerRepository::getOne).collect(Collectors.toList());
                position.setPlayers(players);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Position createdPosition = this.positionRepository.save(position);
        return ResponseEntity.ok(new PositionDTO(createdPosition.getId(), createdPosition.getLabel(), createdPosition.getPlayers().stream().map(Player::getId).collect(Collectors.toList())));
    }

    @DeleteMapping(path = "{id}")
    public void deletePosition(@PathVariable Long id) {
        this.positionRepository.deleteById(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable Long id, @RequestBody PositionDTO positionDTO) {
        Position position;
        try {
            position = this.positionRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        position.setLabel(positionDTO.getLabel());
        List<Player> players;
        if (positionDTO.getPlayers() != null) {
            try {
                players = positionDTO.getPlayers().stream().map(this.playerRepository::getOne).collect(Collectors.toList());
                position.setPlayers(players);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Position updatedPosition = this.positionRepository.save(position);
        return ResponseEntity.ok(new PositionDTO(updatedPosition.getId(), updatedPosition.getLabel(), updatedPosition.getPlayers().stream().map(Player::getId).collect(Collectors.toList())));
    }


}
