package dat3.backend.api;

import dat3.backend.dto.MatchDTO;
import dat3.backend.dto.PatchRefereeDTO;
import dat3.backend.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/matches")
public class MatchController
{
    MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    MatchDTO getMatchById(@PathVariable int matchId) {
        return matchService.getMatchById(matchId);
    }

    @GetMapping()
    List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/accepted")
    List<MatchDTO> getAllAcceptedSignUps(){
        return matchService.getAllAcceptedReferees();
    }

    @PatchMapping()
    PatchRefereeDTO addAccepted(@RequestBody PatchRefereeDTO patchRefereeDTO){
        return matchService.addAccepted(patchRefereeDTO);
    }

    @PostMapping()
    boolean addMatch(@RequestBody MatchDTO matchDTO) {
        return matchService.addMatch(matchDTO);
    }

    @DeleteMapping("/{matchId}")
    boolean deleteMatchById(@PathVariable int matchId) {
        return matchService.deleteMatchById(matchId);
    }

    @GetMapping("/division/{divisionName}")
    List<MatchDTO> getAllMatchesByDivision(@PathVariable String divisionName) {
        return matchService.getAllMatchesByDivisionId(divisionName);
    }

    @GetMapping("/accepted/{username}")
    public List<MatchDTO> getMatches(@PathVariable String username){
        return matchService.getAllAcceptedMatches(username);
    }

    @GetMapping("/signups/{username}")
    public List<MatchDTO> getSignupMatches(@PathVariable String username){
        return matchService.getAllSignUpMatches(username);
    }

}

