package net.chakir.notifservice.repo;

import net.chakir.notifservice.entities.CanalNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanalNotificationRepository extends JpaRepository<CanalNotification, Long> {
}
