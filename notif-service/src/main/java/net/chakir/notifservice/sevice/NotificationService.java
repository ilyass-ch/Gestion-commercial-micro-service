package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Notification notification) {
        // Logique pour envoyer la notification via Kafka
        kafkaTemplate.send("notification-topic", notification);
    }
}
