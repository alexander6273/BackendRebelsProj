package dat3.backend.service;

import dat3.backend.dto.TeamDTO;
import dat3.backend.entity.Team;
import dat3.backend.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class TeamService {
    TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<TeamDTO> getAllTeams(){
        return teamRepository.findAll()
                .stream()
                .map(t -> new TeamDTO(t, true))
                .toList();
    }
    public TeamDTO getTeamById(int id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        return new TeamDTO(team);
    }
}
