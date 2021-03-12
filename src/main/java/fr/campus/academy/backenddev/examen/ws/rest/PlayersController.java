package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.models.Player;
import fr.campus.academy.backenddev.examen.models.Position;
import fr.campus.academy.backenddev.examen.models.Team;
import fr.campus.academy.backenddev.examen.repositories.PlayerRepository;
import fr.campus.academy.backenddev.examen.repositories.PositionRepository;
import fr.campus.academy.backenddev.examen.repositories.TeamRepository;
import fr.campus.academy.backenddev.examen.ws.rest.dto.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "players")
@RequiredArgsConstructor
public class PlayersController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<Player> playerList = this.playerRepository.findAll();
        return ResponseEntity.ok(
                playerList
                        .stream()
                        .map(player -> {
                            List<Long> positions = player.getPositions().stream().map(Position::getId).collect(Collectors.toList());
                            Long teamID = player.getTeam() != null ? player.getTeam().getId() : null;
                            return new PlayerDTO(player.getId(),player.getName(), player.getNumber(), teamID, positions);
                        })
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        try {
            Player player = this.playerRepository.getOne(id);
            List<Long> positions = player.getPositions().stream().map(Position::getId).collect(Collectors.toList());
            Long teamID = player.getTeam() != null ? player.getTeam().getId() : null;
            return ResponseEntity.ok(new PlayerDTO(player.getId(), player.getName(), player.getNumber(), teamID, positions));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> createPlayers(@RequestBody PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setNumber(playerDTO.getNumber());
        Team team;
        if (playerDTO.getTeam() != null) {
            try {
                team = this.teamRepository.getOne(playerDTO.getTeam());
                player.setTeam(team);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        List<Position> positions;
        if (playerDTO.getPositions() != null) {
            try {
                positions = playerDTO.getPositions().stream().map(this.positionRepository::getOne).collect(Collectors.toList());
                player.setPositions(positions);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Player createdPlayer = this.playerRepository.save(player);
        Long teamID = createdPlayer.getTeam() != null ? createdPlayer.getTeam().getId() : null;
        return ResponseEntity.ok(new PlayerDTO(createdPlayer.getId(), createdPlayer.getName(), createdPlayer.getNumber(), teamID, createdPlayer.getPositions().stream().map(Position::getId).collect(Collectors.toList())));
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayer(@PathVariable Long id) {
        this.playerRepository.deleteById(id);
    }

    @PutMapping(path = "{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player player;
        try {
            player = this.playerRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        player.setName(playerDTO.getName());
        player.setNumber(playerDTO.getNumber());
        Team team;
        if (playerDTO.getTeam() != null) {
            try {
                team = this.teamRepository.getOne(playerDTO.getTeam());
                player.setTeam(team);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            player.setTeam(null);
        }
        List<Position> positions;
        if (playerDTO.getPositions() != null) {
            try {
                positions = playerDTO.getPositions().stream().map(this.positionRepository::getOne).collect(Collectors.toList());
                player.setPositions(positions);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        Player updatedPlayer = this.playerRepository.save(player);
        Long teamID = updatedPlayer.getTeam() != null ? updatedPlayer.getTeam().getId() : null;
        return ResponseEntity.ok(new PlayerDTO(updatedPlayer.getId(), updatedPlayer.getName(), updatedPlayer.getNumber(), teamID, updatedPlayer.getPositions().stream().map(Position::getId).collect(Collectors.toList())));
    }

}
