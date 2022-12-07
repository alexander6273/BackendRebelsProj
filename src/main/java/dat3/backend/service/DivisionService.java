package dat3.backend.service;

import dat3.backend.dto.DivisionDTO;
import dat3.backend.repository.DivisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {
    DivisionRepository divisionRepository;
    public DivisionService(DivisionRepository divisionRepository){
        this.divisionRepository = divisionRepository;
    }
    public List<DivisionDTO> getAllDivisions(){
        return divisionRepository.findAll().stream().map(divison -> new DivisionDTO(divison)).toList();
    }

}
