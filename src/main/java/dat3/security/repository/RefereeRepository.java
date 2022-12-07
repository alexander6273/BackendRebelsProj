package dat3.security.repository;

import dat3.backend.entity.Club;
import dat3.backend.entity.Referee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RefereeRepository extends JpaRepository<Referee, String> {

//    @Query("select name from Club where username=Referee.username")
//    Club findClub(String username);


}
