package fr.campus.academy.backenddev.examen.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer number;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name="position_player",
            joinColumns = { @JoinColumn(name = "player_id")},
            inverseJoinColumns = { @JoinColumn(name = "position_id")}
    )
    private List<Position> positions;

}
