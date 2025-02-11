package net.chakir.userservice.repo;

import net.chakir.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByUserName(String username);
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByVerificationCode(String code);
}
