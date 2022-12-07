package dat3.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SignUp
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @ManyToOne
    Match match;
    @ManyToOne
    Referee referee;
    String position;
    public SignUp(Match match, Referee referee, String position) {
        this.match = match;
        this.referee = referee;
        this.position = position;
    }
}
