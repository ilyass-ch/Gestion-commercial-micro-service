package net.chakir.notifservice.repo;

import net.chakir.notifservice.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByClientId(Long clientId);  // Récupérer les notifications d'un client par son ID
    List<Notification> findByUserId(Long userId);  // Récupérer les notifications d'un user par son ID
}
