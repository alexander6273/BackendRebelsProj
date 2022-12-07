package dat3.backend.api;

import dat3.backend.dto.ClubDTO;
import dat3.backend.service.ClubService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/clubs")
public class ClubController {

    ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDTO> getAllClubs(){
        return clubService.getAllClubs();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public void addClub(@RequestBody ClubDTO clubDTO){
        clubService.addClub(clubDTO);
    }

    @GetMapping("/{clubName}")
    public ClubDTO getClubByName(@PathVariable String clubName){
        return clubService.getClubByName(clubName);
    }





}
