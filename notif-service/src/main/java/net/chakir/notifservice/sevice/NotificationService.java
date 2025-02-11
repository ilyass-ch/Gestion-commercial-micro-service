package net.chakir.notifservice.sevice;

import jakarta.mail.internet.MimeMessage;
import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.repo.NotificationRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;  // Pour l'envoi d'email
    private final KafkaTemplate<String, Notification> kafkaTemplate;  // Pour l'envoi via Kafka
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(KafkaTemplate<String, Notification> kafkaTemplate, JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
    }

    // Méthode principale pour envoyer une notification
    public void sendNotification(Notification notification) {
        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);
        System.out.println("Notification sent");
        System.out.println("Notification enregistrée dans la base de données.");

        if ("Email".equals(notification.getType())) {
            sendEmail(notification);  // Envoi de l'email
        } else if ("SMS".equals(notification.getType())) {
            sendSms(notification);  // Envoi du SMS
        } else if ("Kafka".equals(notification.getType())) {
            sendNotificationToKafka(notification);  // Envoi via Kafka
        } else {
            System.out.println("Type de notification inconnu.");
        }
    }

    // Méthode pour envoyer un email
    private void sendEmail(Notification notification) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  // true pour activer l'envoi HTML

            // Récupérer l'email du client
            String clientEmail = notification.getClientEmail();
            if (clientEmail != null && !clientEmail.isEmpty()) {
                helper.setTo(clientEmail);  // Utiliser l'email réel du client
            } else {
                System.out.println("Email du client non fourni.");
                return;  // Si l'email est vide, on ne continue pas l'envoi
            }

            // Sujet de l'email
            helper.setSubject("Confirmation de votre paiement");

            // Contenu HTML de l'email avec styles en ligne
            String emailContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif; color: #333; padding: 20px;'>" +
                    "<div style='background-color: #f4f4f4; padding: 30px; border-radius: 10px;'>" +
                    "<h2 style='color: #4CAF50;'>Bonjour " + notification.getClient().getNom() + ",</h2>" +
                    "<p style='font-size: 16px;'>Nous vous confirmons que votre paiement de " + notification.getMontant() + " Dhs a été accepté. Voici les détails :</p>" +
                    "<table border='1' cellpadding='10' style='border-collapse: collapse; width: 100%; margin-bottom: 20px;'>" +
                    "<tr><td><strong>Montant payé</strong></td><td>" + notification.getMontant() + " Dhs</td></tr>" +
                    "<tr><td><strong>Mode de paiement</strong></td><td>" + notification.getMethodPaiement() + "</td></tr>" +
                    "<tr><td><strong>Date de paiement</strong></td><td>" + notification.getDate() + "</td></tr>" +
                    "</table>" +
                    "<p style='font-size: 16px;'>Nous vous remercions pour votre achat. Vous pouvez visualiser votre historique à tout moment dans votre compte.</p>" +
                    "<div style='text-align: center; margin-top: 20px;'>" +
                    "<p style='font-size: 14px;'>Si vous avez des questions, contactez-nous à pfe@chakir.com.</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Ajouter le contenu HTML à l'email
            helper.setText(emailContent, true);  // true pour indiquer que le contenu est HTML

            // Envoi de l'email
            mailSender.send(message);
            System.out.println("Email envoyé avec succès!");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }
    }
    // Méthode pour envoyer un SMS
    private void sendSms(Notification notification) {
        // Logique pour envoyer un SMS via une API externe (ex. Twilio, Nexmo, etc.)
        System.out.println("Envoi du SMS: " + notification.getContenu());
    }

    // Méthode pour envoyer la notification via Kafka
    private void sendNotificationToKafka(Notification notification) {
        // Logique pour envoyer la notification via Kafka
        kafkaTemplate.send("notification-topic", notification);  // Envoi à un topic Kafka
        System.out.println("Notification envoyée à Kafka.");
    }
}
