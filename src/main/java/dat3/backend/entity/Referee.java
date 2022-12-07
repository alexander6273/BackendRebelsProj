package dat3.backend.entity;

import dat3.security.entity.UserWithRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referee extends UserWithRoles {

    @Column(name = "kampe")
    @ManyToMany()
    List<Match> matches = new ArrayList<>();

    String position;

    License license;

    @Column(name = "bankOplysninger")
    String bankInformation;

    @ManyToOne
    Club club;

    public Referee(String username, String password,
                   String email, String firstname,
                   String lastname, String position,
                   String bankInformation ) {
        super(username, password, email, firstname, lastname);
        this.position = position;
        this.bankInformation = bankInformation;
    }

    public  Referee(String username, String password, String email, String firstname, String lastname){
        super(username,password, email, firstname, lastname);
    }

    public void setLicense(String license){
        switch (license) {
            case "A" -> this.license = License.A;
            case "B" -> this.license = License.B;
            case "C" -> this.license = License.C;
            case "D" -> this.license = License.D;
            case "I" -> this.license = License.I;
        }
    }
    public void addAcceptedMatch(Match match){
        matches.add(match);
    }
}
