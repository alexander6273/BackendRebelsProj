package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchRefereeDTO
{
    private int matchId;
    private String username;
    private int signupId;

    public PatchRefereeDTO(int matchId, String username, int signupId) {
        this.matchId = matchId;
        this.username = username;
        this.signupId = signupId;
    }

}
