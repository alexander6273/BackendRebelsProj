package dat3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Division {
    @Id
    String name;
    int matchTime;
    int commission;
    String license;
    @OneToMany
    List<Team> teams;
    @OneToMany
    List<Match> matches;
    int numberOfReferees;

    public Division(String name, int matchTime, int commission, String license, int numberOfReferees) {
        this.name = name;
        this.matchTime = matchTime;
        this.commission = commission;
        this.license = license;
        this.numberOfReferees = numberOfReferees;
    }
}
