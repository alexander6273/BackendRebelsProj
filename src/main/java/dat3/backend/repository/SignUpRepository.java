package dat3.backend.repository;

import dat3.backend.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignUpRepository extends JpaRepository<SignUp, Integer> {
    List<SignUp> findAllByMatch_Id(int id);
    List<SignUp> findAllByReferee_Username(String username);
}
