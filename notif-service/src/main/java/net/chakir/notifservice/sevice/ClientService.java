package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public ClientService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Méthode pour récupérer les notifications d'un client par son ID
    public List<Notification> getNotificationsByClientId(Long clientId) {
        return notificationRepository.findByClientId(clientId);  // Requête pour récupérer les notifications par ID client
    }
}
