package dat3.backend.api;

import dat3.backend.dto.SignUpDTO;
import dat3.backend.service.SignUpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/signups")
public class SignUpController {
    SignUpService signUpService;
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/{signupId}")
    public SignUpDTO getSignUpById(@PathVariable int signupId){
        return signUpService.getSignUpById(signupId);
    }

    @GetMapping("/findSignups/{matchId}")
    public List<SignUpDTO> getAllSignupsByMatchId(@PathVariable int matchId){
        return signUpService.getAllSignUpsByMatchId(matchId);
    }

    @PostMapping()
    public SignUpDTO addSignUp(@RequestBody SignUpDTO signUpDTO) {
        return signUpService.addSignUp(signUpDTO);
    }
}
