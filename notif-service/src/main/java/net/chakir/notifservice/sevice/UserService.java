package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.repo.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private final NotificationRepository notificationRepository;

    public UserService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByUSerId(long userId) {
        return notificationRepository.findByUserId(userId);
    }

}
