package dat3.backend.service;

import dat3.backend.dto.ClubDTO;
import dat3.backend.entity.Club;
import dat3.backend.repository.ClubRepository;
import dat3.security.repository.RefereeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClubService {
    ClubRepository clubRepository;
    RefereeRepository refereeRepository;
    public ClubService(ClubRepository clubRepository, RefereeRepository refereeRepository){
        this.refereeRepository = refereeRepository;
        this.clubRepository = clubRepository;
    }

    public List<ClubDTO> getAllClubs(){
       return clubRepository.findAll().stream().map(
               club -> new ClubDTO(club)).toList();
    }

    public void addClub(ClubDTO clubDTO){
        Club club = new Club(clubDTO.getName(), clubDTO.getAddress(), clubDTO.getEmail());
        if(clubRepository.existsById(clubDTO.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Klubben eksisterer allerede");
        }else {
            clubRepository.save(club);
        }
    }
    public ClubDTO getClubByName(String clubName) {
        return clubRepository.findById(clubName).map(
                club -> new ClubDTO(club))
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Club not found"));
    }
}
