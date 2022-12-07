package dat3.backend.repository;

import dat3.backend.entity.Division;
import dat3.backend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Integer>
{
    List<Match> findAllByDivision(Division division);
}
