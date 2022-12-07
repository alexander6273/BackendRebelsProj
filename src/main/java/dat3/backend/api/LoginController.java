package dat3.backend.api;


import dat3.backend.dto.UsernameDTO;
import dat3.backend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {
    UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String getInfoNotLoggedIn() {
        return "Hello i am not logged in";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public boolean getUserAdminInfo() {
        return true;
    }

    @PreAuthorize("hasAuthority('REFEREE')")
    @GetMapping("/referee")
    public boolean getRefereeInfo() {
        return true;
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/user")
    public boolean getUserInfo() {
        return true;
    }
    @GetMapping("/user-fromtoken")
    public UsernameDTO getUserInfo(Principal p) {
        String username = p.getName();
        return userService.getUserNameDTO(username);
    }
}
