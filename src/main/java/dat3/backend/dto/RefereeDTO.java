package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Referee;
import dat3.security.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefereeDTO {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;
    String position;
    String license;
    String bankInformation;
    List<Role> roles;
    String clubName;
    List<MatchDTO> matches;

    public RefereeDTO(String username, String password, String email, String firstname, String lastname, String position, String bankInformation) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.license = license;
        this.bankInformation = bankInformation;
    }
    public RefereeDTO(Referee r){
        this.username = r.getUsername();
        this.password = r.getPassword();
        this.email = r.getEmail();
        this.firstname = r.getFirstname();
        this.lastname = r.getLastname();
        this.position = r.getPosition();
        this.license = r.getLicense().toString();
        this.bankInformation = r.getBankInformation();
        this.roles = r.getRoles();
        if(!(r.getClub() == null)){
        this.clubName = r.getClub().getName();
    }}
}
