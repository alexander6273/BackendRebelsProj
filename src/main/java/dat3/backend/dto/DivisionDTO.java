package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Division;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DivisionDTO {
        String name;
        int matchTime;
        int commission;
        String license;

        public DivisionDTO(Division d) {
            this.name = d.getName();
            this.matchTime = d.getMatchTime();
            this.commission = d.getCommission();
            this.license = d.getLicense();
        }

}
