package dat3.backend.api;

import dat3.backend.dto.TeamDTO;
import dat3.backend.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/teams")
public class TeamController
{
    TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{teamId}")
    public TeamDTO getTeamById(@PathVariable int teamId){
        return teamService.getTeamById(teamId);
    }

    @GetMapping()
    public List<TeamDTO> getAllTeams(){
        return teamService.getAllTeams();
    }
}
