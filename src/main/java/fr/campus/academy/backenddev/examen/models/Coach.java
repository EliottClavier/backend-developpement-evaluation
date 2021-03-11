package fr.campus.academy.backenddev.examen.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assistant")
    private List<Coach> assistants;

    @ManyToOne()
    @JoinColumn(name = "assistant_id")
    private Coach assistant;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Team team;

}
