package dat3.backend.service;

import dat3.backend.dto.MatchDTO;
import dat3.backend.dto.PatchRefereeDTO;
import dat3.backend.entity.*;
import dat3.backend.repository.DivisionRepository;
import dat3.backend.repository.MatchRepository;
import dat3.backend.repository.SignUpRepository;
import dat3.backend.repository.TeamRepository;
import dat3.security.repository.RefereeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatchService {
    MatchRepository matchRepository;
    TeamRepository teamRepository;
    SignUpRepository signUpRepository;
    RefereeRepository refereeRepository;
    DivisionRepository divisionRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, SignUpRepository signUpRepository, RefereeRepository refereeRepository, DivisionRepository divisionRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.signUpRepository = signUpRepository;
        this.refereeRepository = refereeRepository;
        this.divisionRepository = divisionRepository;
    }

    public MatchDTO getMatchById(int id){
        Match foundMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        return new MatchDTO(foundMatch, true);
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .sorted((o1,o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
                .map(m -> new MatchDTO(m, true))
                .toList();
    }

    public List<MatchDTO> getAllAcceptedReferees(){
        return matchRepository.findAll()
                .stream()
                .map(a -> new MatchDTO(a, true))
                .toList();
    }

    public boolean deleteMatchById(int matchId) {
        try {
            Match match = matchRepository.findById(matchId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
            matchRepository.delete(match);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addMatch(MatchDTO matchDTO) {
        try {
            Team homeTeam = teamRepository.findById(matchDTO.getHomeTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hjemmehold ikke fundet"));
            Team awayTeam = teamRepository.findById(matchDTO.getAwayTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Udehold ikke fundet"));
            Team refereeTeam = teamRepository.findById(matchDTO.getRefereeTeamId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dommerhold ikke fundet"));

            Match createNewMatch = matchDTO.getMatchEntity(matchDTO, homeTeam, awayTeam, refereeTeam);
            matchRepository.save(createNewMatch);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PatchRefereeDTO addAccepted(PatchRefereeDTO patchRefereeDTO){
        Match foundMatch = matchRepository.findById(patchRefereeDTO.getMatchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kampen blev ikke fundet"));;
        Referee foundReferee = refereeRepository.findById(patchRefereeDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dommeren blev ikke fundet"));
        SignUp foundSignup = signUpRepository.findById(patchRefereeDTO.getSignupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tilmeldingen blev ikke fundet"));
        if (foundMatch.getAcceptedReferees().size() == foundMatch.getDivision().getNumberOfReferees()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dommerpositionerne er udfyldt");
        }
        if (foundMatch.getAcceptedReferees().contains(foundReferee)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dommeren er allerede godkendt");
        }

        foundMatch.addReferee(foundReferee);
        signUpRepository.delete(foundSignup);
        matchRepository.save(foundMatch);
        return new PatchRefereeDTO(foundMatch.getId(), foundReferee.getUsername(), foundSignup.getId());
    }

    public List<MatchDTO> getAllMatchesByDivisionId(String divisionId) {
        Division divisionFound = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Division not found"));
        return matchRepository.findAllByDivision(divisionFound).stream().map(m -> new MatchDTO(m, true)).toList();
    }

    public List<MatchDTO> getAllAcceptedMatches(String username){
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));

        return  referee.getMatches().stream().map(match -> new MatchDTO(match,true)).toList();
    }

    public List<MatchDTO> getAllSignUpMatches(String username){
        List<SignUp> signUps = signUpRepository.findAllByReferee_Username(username);
        List<Match> matches = signUps.stream().map(signUp -> matchRepository.findById(signUp.getMatch().getId()).orElseThrow()).toList();
        return matches.stream().map(match -> new MatchDTO(match,true)).toList();
    }
}
