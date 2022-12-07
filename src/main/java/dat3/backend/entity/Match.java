package dat3.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "kamp")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    List<SignUp> signUps = new ArrayList<>();

    @ManyToMany()
    List<Referee> acceptedReferees = new ArrayList<>();

    @ManyToOne
    Team homeTeam;

    @ManyToOne
    Team awayTeam;

    @ManyToOne
    Team refereeTeam;

    LocalDateTime startTime;

    @ManyToOne
    Division division;

    public Match(Team homeTeam, Team awayTeam, Team refereeTeam, LocalDateTime startTime, Division division) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.refereeTeam = refereeTeam;
        this.startTime = startTime;
        this.division = division;
    }

    public void addReferee(Referee referee) {
        acceptedReferees.add(referee);
    }
}
