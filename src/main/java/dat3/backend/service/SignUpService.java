package dat3.backend.service;

import dat3.backend.dto.SignUpDTO;
import dat3.backend.entity.Match;
import dat3.backend.entity.Referee;
import dat3.backend.entity.SignUp;
import dat3.backend.repository.MatchRepository;
import dat3.backend.repository.SignUpRepository;
import dat3.security.repository.RefereeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SignUpService {
    SignUpRepository signUpRepository;
    MatchRepository matchRepository;
    RefereeRepository refereeRepository;


    public SignUpService(SignUpRepository signUpRepository, MatchRepository matchRepository, RefereeRepository refereeRepository) {
        this.signUpRepository = signUpRepository;
        this.matchRepository = matchRepository;
        this.refereeRepository = refereeRepository;
    }

    public SignUpDTO getSignUpById(int id) {
        SignUp foundId = signUpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        return new SignUpDTO(foundId, false);
    }

    public List<SignUpDTO> getAllSignUpsByMatchId(int matchId) {
        List<SignUp> foundSignUps = signUpRepository.findAllByMatch_Id(matchId);
        return foundSignUps.stream().map(s -> new SignUpDTO(s, true)).toList();
    }

    public SignUpDTO addSignUp(SignUpDTO signUpDTO) {
        Match match = matchRepository.findById(signUpDTO.getMatchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        Referee referee = refereeRepository.findById(signUpDTO.getRefereeUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not logged in"));
        if (!referee.getClub().getName().equals(match.getRefereeTeam().getClub().getName())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a member of the refeering club");
        }

        List<SignUp> signUps = match.getSignUps();
        for (SignUp signUp : signUps) {
            if (signUp.getReferee() == referee) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Already signed up");
            }
        }

        if (match.getAcceptedReferees().contains(referee)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Already signed up");
        }

        String position = signUpDTO.getPosition();
        SignUp createNewSignup = signUpDTO.getSignUpEntiy(match, referee, position);
        signUpRepository.save(createNewSignup);
        return new SignUpDTO(createNewSignup, true);
    }

    public boolean checkIfSignedUp(List<SignUp> signUps, Referee referee) {
        for (int i = 0; i < signUps.size(); i++) {
            if (signUps.get(i).getReferee() == referee) {
                return true;
            }
        }
        return false;
    }

}


