package dat3.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @Column(unique = true)
    String name;
    String address;
    String email;
    String imageString;
    @OneToMany
    List<Team> teams = new ArrayList<>();

    @OneToMany
    List<Referee> referees = new ArrayList<>();

    public Club(String name, String address, String email) {
        this.name = name.toLowerCase();
        this.address = address;
        this.email = email;
    }

    public Club(String name, String address, String email, String imageString) {
        this.name = name.toLowerCase();
        this.address = address;
        this.email = email;
        this.imageString = imageString;
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public void addReferee(Referee referee){referees.add(referee);}



}
