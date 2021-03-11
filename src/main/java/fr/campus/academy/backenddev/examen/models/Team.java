package fr.campus.academy.backenddev.examen.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "teamName",
            nullable = false,
            length = 100
    )
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "team")
    @JoinColumn(unique = true)
    private Coach coach;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "team")
    private List<Player> players;

}
