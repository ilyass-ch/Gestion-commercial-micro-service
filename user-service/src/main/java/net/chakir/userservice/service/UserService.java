package net.chakir.userservice.service;

import net.chakir.userservice.entities.Role;
import net.chakir.userservice.entities.User;
import net.chakir.userservice.repo.RoleRepository;
import net.chakir.userservice.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
    }


    public User createUser(User user) {

        return userRepository.save(user);
    }


    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }


    public Optional<User> getUserByUsername(String username) {
        List<User> users = userRepository.findByUserName(username);
        if (!users.isEmpty()) {
            return Optional.of(users.get(0)); // Prend le premier utilisateur trouvé
        }
        return Optional.empty();
    }



    public Optional<User> authenticateUser(String email, String password) {
        return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
    }

    public User assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        user.setUserName(role.getRoleName()); // Ajoute le rôle à la liste des rôles de l'utilisateur
        return userRepository.save(user); // Sauvegarde les changements
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
