package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "commande-topic", groupId = "notification-group")
    public void consumeCommandeEvent(String commandeEvent) {
        // Logique pour créer une notification à partir de l'événement reçu
        Notification notification = new Notification();
        notification.setType("Email");
        notification.setContenu("Votre commande a été validée !");
        notificationService.sendNotification(notification);
    }

    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentEvent(String paymentEvent) {
        // Logique pour créer une notification à partir de l'événement de paiement
        Notification notification = new Notification();
        notification.setType("SMS");
        notification.setContenu("Votre paiement a été accepté.");
        notificationService.sendNotification(notification);
    }
}

