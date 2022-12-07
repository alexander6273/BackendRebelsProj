package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClubDTO {
    String name;
    String address;
    String email;
    List<Integer> teams;
    List<String> referees;
    String imageString;


    public ClubDTO(Club c) {
        this.name = c.getName();
        this.address = c.getAddress();
        this.email = c.getEmail();
        this.teams = c.getTeams().stream().map(team -> team.getId()).toList();
        this.referees = c.getReferees().stream().map(referee -> referee.getUsername()).toList();
        this.imageString = c.getImageString();
    }

}
