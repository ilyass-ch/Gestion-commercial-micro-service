package net.chakir.userservice;


import net.chakir.userservice.entities.Role;
import net.chakir.userservice.entities.User;
import net.chakir.userservice.service.RoleService;
import net.chakir.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner( RoleService roleService, UserService userService) {
        return args -> {

            Role adminRole = roleService.createRole(new Role(null, "ADMIN"));
            Role userRole = roleService.createRole(new Role(null, "USER"));

            // Créer un utilisateur avec le rôle ADMIN
            User adminUser1 = new User(
                    null,
                    "admin",
                    "admin@example.com",
                    "password123",
                    List.of(adminRole), // Rôle persistant
                    LocalDateTime.now()
            );
            userService.createUser(adminUser1);
            User adminUser2 = new User(
                    null,
                    "user",
                    "user@example.com",
                    "password123",
                    List.of(userRole), // Rôle persistant
                    LocalDateTime.now()
            );
            userService.createUser(adminUser2);

        };
    }






}
