package dat3.backend.service;


import dat3.backend.dto.RefereeDTO;
import dat3.backend.dto.UsernameDTO;
import dat3.backend.entity.Referee;
import dat3.backend.repository.SignUpRepository;
import dat3.security.dto.UserWithRolesRequest;
import dat3.security.entity.Role;
import dat3.security.repository.RefereeRepository;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    UserWithRolesRepository userWithRolesRepository;
    RefereeRepository refereeRepository;
    SignUpRepository signUpRepository;
    public UserService(UserWithRolesRepository userWithRolesRepository, RefereeRepository refereeRepository, SignUpRepository signUpRepository){
        this.userWithRolesRepository = userWithRolesRepository;
        this.refereeRepository = refereeRepository;
        this.signUpRepository = signUpRepository;
    }
    public UsernameDTO addReferee(RefereeDTO refereeDTO) {
        if(userWithRolesRepository.existsById(refereeDTO.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username er taget");
        } else if (userWithRolesRepository.existsByEmail(refereeDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email er allerede i brug");
        } else {
            Referee newUser = new Referee(refereeDTO.getUsername(),
                    refereeDTO.getPassword(),
                    refereeDTO.getEmail(),
                    refereeDTO.getFirstname(),
                    refereeDTO.getLastname(),
                    refereeDTO.getPosition(),
                    refereeDTO.getBankInformation());
            newUser.setLicense(refereeDTO.getLicense());

            newUser.addRole(Role.REFEREE);
            userWithRolesRepository.save(newUser);

            return new UsernameDTO(newUser.getUsername());
        }
    }

    public List<UserWithRolesRequest> getAllUsers(){
        return userWithRolesRepository.findAll().stream().map(user -> new UserWithRolesRequest(user)).toList();
    }
    public List<RefereeDTO> getAllReferees(){
        return refereeRepository.findAll().stream().map(ref -> new RefereeDTO(ref)).toList();
    }
    public UsernameDTO editReferee(String username, RefereeDTO refereeDTO) {
        Referee refereeToEdit = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));
        if(!refereeToEdit.getEmail().equals(refereeDTO.getEmail()) &&  userWithRolesRepository.existsByEmail(refereeDTO.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email er allerede i brug");
        }
        else {
            refereeToEdit.setFirstname(refereeDTO.getFirstname());
            refereeToEdit.setLastname(refereeDTO.getLastname());
            refereeToEdit.setEmail(refereeDTO.getEmail());
            refereeToEdit.setBankInformation(refereeDTO.getBankInformation());
            refereeToEdit.setLicense(refereeDTO.getLicense());

            refereeRepository.save(refereeToEdit);
            return new UsernameDTO(refereeToEdit.getUsername());
        }
    }
    public RefereeDTO getReferee(String username) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));

        return new RefereeDTO(referee);
    }

    public UsernameDTO getUserNameDTO(String username) {
        return new UsernameDTO(username);
    }

    public void editRefereePassword(String username, RefereeDTO refereeDTO) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));
        referee.setPassword(refereeDTO.getPassword());
        refereeRepository.save(referee);
    }

    public void makeAdmin(String username) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));
        if(!referee.getRoles().contains(Role.ADMIN)){
            referee.addRole(Role.ADMIN);
            refereeRepository.save(referee);
        }
    }
}
