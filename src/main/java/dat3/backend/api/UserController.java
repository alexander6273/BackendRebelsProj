package dat3.backend.api;

import dat3.backend.dto.RefereeDTO;
import dat3.backend.dto.UsernameDTO;
import dat3.backend.service.ClubService;
import dat3.backend.service.UserService;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
    ClubService clubService;
    public UserController(UserService userService, ClubService clubService){
        this.userService = userService;
        this.clubService = clubService;
    }



    @PostMapping("/referee")
    public UsernameDTO createReferee(@RequestBody RefereeDTO refereeDTO ){
        return  userService.addReferee(refereeDTO);
    }

    @GetMapping()
    public List<UserWithRolesRequest> getAllUsers(){
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/referees")
    public List<RefereeDTO> getAllReferees(){
        return userService.getAllReferees();
    }

    @PatchMapping("/referee")
    public UsernameDTO editReferee(Principal p, @RequestBody RefereeDTO refereeDTO){
      return userService.editReferee(p.getName(), refereeDTO);
    }

    @GetMapping("/referee")
        public RefereeDTO getReferee(Principal p){
        return userService.getReferee(p.getName());
    }

    @GetMapping("/referee/{refereeName}")
    public RefereeDTO getRefereeInfo(@PathVariable String refereeName){
        return userService.getReferee(refereeName);
    }

    @PatchMapping("/refereePassword")
    public void editRefereePassword(Principal p, @RequestBody RefereeDTO refereeDTO) {
        userService.editRefereePassword(p.getName(), refereeDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/makeAdmin/{username}")
    public void makeAdmin(@PathVariable String username){
        userService.makeAdmin(username);
    }



}
