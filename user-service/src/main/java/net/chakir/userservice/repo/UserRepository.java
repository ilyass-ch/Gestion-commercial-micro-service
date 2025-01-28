package net.chakir.userservice.repo;

import net.chakir.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByUserName(String username);
    User findByEmailAndPassword(String email, String password);
}
