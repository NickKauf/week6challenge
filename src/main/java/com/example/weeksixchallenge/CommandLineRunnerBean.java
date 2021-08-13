package com.example.weeksixchallenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;


    public void run(String...args){
        User admin = new User("admin", "admin", true);
        Role adminRole = new Role("admin", "ROLE_USER");
        Role adminRole2 = new Role("admin", "ROLE_ADMIN");

        userRepository.save(admin);
        roleRepository.save(adminRole);
        roleRepository.save(adminRole2);

        User user = new User("user", "user", true);
        Role userRole = new Role("user", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);

        Team team1 = new Team();
        team1.setCity("Rockville");
        team1.setName("Cicadas");

        Player playerA1 = new Player(
                "Billy",
                "Mitchell",
                12,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team1);

        Player playerA2 = new Player(
                "Eliza",
                "Mitchell",
                11,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team1);

        Set<Player> players1 = new HashSet<>();
        players1.add(playerA1);
        players1.add(playerA2);
        team1.setPlayers(players1);

        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setCity("Bethesda");
        team2.setName("Blue Crabs");

        Player playerB1 = new Player(
                "Bryan",
                "Fields",
                14,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team2);

        Player playerB2 = new Player(
                "Jane",
                "Simpson",
                13,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team2);

        Set<Player> players2 = new HashSet<>();
        players2.add(playerB1);
        players2.add(playerB2);
        team2.setPlayers(players2);

        teamRepository.save(team2);

        Team team3 = new Team();
        team3.setCity("Potomac");
        team3.setName("Woodpeckers");

        Player playerC1 = new Player(
                "Tommy",
                "Bobinski",
                16,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team3);

        Player playerC2 = new Player(
                "Timmy",
                "Bobinski",
                9,
                "https://res.cloudinary.com/nk4363/image/upload/v1628864009/placeholder_znzhxi.png",
                team3);

        Set<Player> players3 = new HashSet<>();
        players3.add(playerC1);
        players3.add(playerC2);
        team3.setPlayers(players3);

        teamRepository.save(team3);



    }


}
