package dat3.backend.configuration;

import dat3.backend.entity.*;
import dat3.backend.repository.*;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    TeamRepository teamRepository;
    SignUpRepository signUpRepository;
    MatchRepository matchRepository;
    ClubRepository clubRepository;

    DivisionRepository divisionRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, TeamRepository teamRepository, SignUpRepository signUpRepository, MatchRepository matchRepository, ClubRepository clubRepository, DivisionRepository divisionRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.teamRepository = teamRepository;
        this.signUpRepository = signUpRepository;
        this.matchRepository = matchRepository;
        this.clubRepository = clubRepository;
        this.divisionRepository = divisionRepository;

        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        Referee user1 = new Referee("KC",  passwordUsedByAll, "KCRebels@a.dk", "Frederik", "Kiær");
        Referee user2 = new Referee("user2",  passwordUsedByAll, "user2@a.dk", "Mikkel", "Frederiksen");
        Referee user3 = new Referee("user3",  passwordUsedByAll, "user3@a.dk", "Robert", "Mikkelsen");
        Referee user4 = new Referee("user4",  passwordUsedByAll, "user4@a.dk", "Karsten", "Karstensen");
        Referee user5 = new Referee("user5",  passwordUsedByAll, "user5@a.dk", "Christina", "Petersen");


        user1.setLicense("A");
        user2.setLicense("B");
        user3.setLicense("C");
        user4.setLicense("D");
        user5.setLicense("I");


        user1.addRole(Role.ADMIN);
        user1.addRole(Role.REFEREE);
        user2.addRole(Role.REFEREE);
        user3.addRole(Role.REFEREE);
        user4.addRole(Role.REFEREE);
        user5.addRole(Role.REFEREE);

        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(user5);

        Referee referee1 = new Referee("Lars", passwordUsedByAll, "Lars@a.dk","Lars", "Olsen");
        Referee referee2 = new Referee("Hans", passwordUsedByAll, "Hans@a.dk","Hans", "Hansen");
        Referee referee3 = new Referee("Bo", passwordUsedByAll, "BoLarsen@a.dk","Bo", "Larsen");
        Referee referee4 = new Referee("Allan", passwordUsedByAll, "AllanJeppesen@a.dk","Allan", "Jeppesen");
        Referee referee5 = new Referee("Per", passwordUsedByAll, "Per@a.dk","Per", "Olsen");

        referee1.addRole(Role.REFEREE);
        referee2.addRole(Role.REFEREE);
        referee3.addRole(Role.REFEREE);
        referee4.addRole(Role.REFEREE);
        referee5.addRole(Role.REFEREE);


        referee1.setLicense("A");
        referee2.setLicense("A");
        referee2.setLicense("B");
        referee3.setLicense("B");
        referee4.setLicense("C");
        referee5.setLicense("C");


        userWithRolesRepository.save(referee1);
        userWithRolesRepository.save(referee2);
        userWithRolesRepository.save(referee3);
        userWithRolesRepository.save(referee4);
        userWithRolesRepository.save(referee5);


        Division division1 = new Division("U13",90,100, "D",2);
        Division division2 = new Division("U15",90,100, "C",3);
        Division division3 = new Division("U17",90,100, "B",4);
        Division division4 = new Division("U19",90,100, "A",5);

        divisionRepository.save(division1);
        divisionRepository.save(division2);
        divisionRepository.save(division3);
        divisionRepository.save(division4);

        Team team1 = new Team("U13", division1);
        Team team2 = new Team("U13", division1);
        Team team3 = new Team("U13", division1);

        Team team4 = new Team("U15", division2);
        Team team5 = new Team("U15", division2);
        Team team6 = new Team("U15", division2);

        Team team7 = new Team("U17", division3);
        Team team8 = new Team("U17", division3);
        Team team9 = new Team("U17", division3);

        Team team10 = new Team("U19", division4);
        Team team11 = new Team("U19", division4);
        Team team12 = new Team("U19", division4);

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);
        teamRepository.save(team5);
        teamRepository.save(team6);
        teamRepository.save(team7);
        teamRepository.save(team8);
        teamRepository.save(team9);
        teamRepository.save(team10);
        teamRepository.save(team11);
        teamRepository.save(team12);

        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 12, 30);
        LocalDateTime ldt2 = LocalDateTime.of(2023, 5, 10, 13, 15);
        LocalDateTime ldt3 = LocalDateTime.of(2023, 3, 9, 11, 45);
        LocalDateTime ldt4 = LocalDateTime.of(2023, 2, 22, 10, 30);
        LocalDateTime ldt5 = LocalDateTime.of(2023, 2, 21, 12, 15);
        LocalDateTime ldt6 = LocalDateTime.of(2023, 1, 10, 13, 15);
        LocalDateTime ldt7 = LocalDateTime.of(2023, 3, 11, 14, 0);
        LocalDateTime ldt8 = LocalDateTime.of(2023, 3, 24, 11, 15);
        LocalDateTime ldt9 = LocalDateTime.of(2023, 4, 4, 12, 0);
        LocalDateTime ldt10 = LocalDateTime.of(2023, 2, 9, 12, 0);

        Match match1 = new Match(team1, team3, team2, ldt1, division1);
        Match match2 = new Match(team1, team2, team3, ldt2, division1);
        Match match3 = new Match(team2, team1, team3, ldt3, division1);
        Match match4 = new Match(team2, team3, team1, ldt4, division1);
        Match match5 = new Match(team4, team5, team6, ldt5, division2);
        Match match6 = new Match(team7, team8, team9, ldt6, division3);
        Match match7 = new Match(team8, team7, team9, ldt7, division3);
        Match match8 = new Match(team9, team7, team8, ldt8, division3);
        Match match9 = new Match(team10, team11, team12, ldt9, division4);
        Match match10 = new Match(team11, team12, team10, ldt10, division4);

        matchRepository.save(match1);
        matchRepository.save(match2);
        matchRepository.save(match3);
        matchRepository.save(match4);
        matchRepository.save(match5);
        matchRepository.save(match6);
        matchRepository.save(match7);
        matchRepository.save(match8);
        matchRepository.save(match9);
        matchRepository.save(match10);

        SignUp signUp1 = new SignUp(match2, referee2, "Head Judge");
        SignUp signUp2 = new SignUp(match2, referee1, "Back Judge");
        SignUp signUp4 = new SignUp(match4, referee1, "Line Judge");
        SignUp signUp5 = new SignUp(match4, referee2, "Field Judge");
        signUpRepository.save(signUp1);
        signUpRepository.save(signUp2);
        signUpRepository.save(signUp4);
        signUpRepository.save(signUp5);

        List<Referee> acceptedReferees = new ArrayList<>();
        acceptedReferees.add(referee1);
        acceptedReferees.add(referee2);


        Match match20 = new Match(team2, team1, team3, ldt1, division1);
        matchRepository.save(match20);
        match20.setAcceptedReferees(acceptedReferees);
        match1.setAcceptedReferees(acceptedReferees);

        referee1.addAcceptedMatch(match1);
        referee2.addAcceptedMatch(match1);

        referee1.addAcceptedMatch(match20);
        referee2.addAcceptedMatch(match20);

        matchRepository.save(match20);
        matchRepository.save(match1);

        Club club1 = new Club("Herlev Rebels", "Dilhaven 40, 2730 Herlev", "Herlev@Rebels.com", "rebels");
        Club club2 = new Club("Copenhagen Towers", "Ved Stadion 6, 2820 Gentofte", "Copenhagen@Towers.com", "towers");
        Club club3 = new Club("Søllerød Golddiggers", "Egebækvej 118, 2850 Nærum", "Søllerød@GoldDiggers.com", "golddiggers");
        Club club4 = new Club("Club4", "Club4Sted", "club@club4.com");
        Club club5 = new Club("Club5", "Club5Sted", "club@club5.com");

        club1.addTeam(team1);
        club2.addTeam(team2);
        club3.addTeam(team3);
        club1.addTeam(team4);
        club2.addTeam(team5);
        club3.addTeam(team6);
        club1.addTeam(team7);
        club2.addTeam(team8);
        club3.addTeam(team9);
        club1.addTeam(team10);
        club2.addTeam(team11);
        club3.addTeam(team12);


        team1.setClub(club1);
        team2.setClub(club2);
        team3.setClub(club3);
        team4.setClub(club1);
        team5.setClub(club2);
        team6.setClub(club3);
        team7.setClub(club1);
        team8.setClub(club2);
        team9.setClub(club3);
        team10.setClub(club1);
        team11.setClub(club2);
        team12.setClub(club3);

        user1.setClub(club1);
        user2.setClub(club2);
        user3.setClub(club3);
        user4.setClub(club4);
        user5.setClub(club5);

        referee1.setClub(club1);
        referee2.setClub(club2);
        referee3.setClub(club3);
        referee4.setClub(club4);
        referee5.setClub(club5);


        club1.addReferee(user1);
        club1.addReferee(referee1);

        club2.addReferee(user2);
        club2.addReferee(referee2);
        club3.addReferee(user3);
        club3.addReferee(referee3);
        club4.addReferee(user4);
        club4.addReferee(referee4);
        club5.addReferee(user5);
        club5.addReferee(referee5);

        clubRepository.save(club1);
        clubRepository.save(club2);
        clubRepository.save(club3);
        clubRepository.save(club4);
        clubRepository.save(club5);

        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);
        teamRepository.save(team5);
        teamRepository.save(team6);
        teamRepository.save(team7);
        teamRepository.save(team8);
        teamRepository.save(team9);
        teamRepository.save(team10);
        teamRepository.save(team11);
        teamRepository.save(team12);

        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(user5);
        userWithRolesRepository.save(referee1);
        userWithRolesRepository.save(referee2);
        userWithRolesRepository.save(referee3);
        userWithRolesRepository.save(referee4);
        userWithRolesRepository.save(referee5);

        // DOMMERANSVARLIG
        Referee refManagerHerlev = new Referee("herlev", passwordUsedByAll, "refman1@a.dk","Kristian", "Hadberg");
        refManagerHerlev.addRole(Role.REFEREE);
        refManagerHerlev.addRole(Role.REFEREEMANAGER);
        refManagerHerlev.setLicense("A");
        refManagerHerlev.setClub(club1);
        userWithRolesRepository.save(refManagerHerlev);
        club1.addReferee(refManagerHerlev);
        clubRepository.save(club1);

        Referee refManagerCopenhagen = new Referee("copenhagen", passwordUsedByAll, "refman2@a.dk","Alexander", "Larsen");
        refManagerCopenhagen.addRole(Role.REFEREE);
        refManagerCopenhagen.addRole(Role.REFEREEMANAGER);
        refManagerCopenhagen.setLicense("A");
        refManagerCopenhagen.setClub(club2);
        userWithRolesRepository.save(refManagerCopenhagen);

    }

}
