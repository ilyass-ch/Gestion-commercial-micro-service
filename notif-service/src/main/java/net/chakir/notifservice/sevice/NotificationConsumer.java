package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.model.Client;
import net.chakir.notifservice.model.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentEvent(String paymentEvent) {
        // Logique pour créer une notification à partir de l'événement de paiement
        Notification notification = new Notification();
        notification.setType("Email");  // Type de notification : Email
        notification.setContenu("Votre paiement a été accepté.");

        try {
            // Utilisation de Jackson pour parser l'événement JSON et récupérer l'objet PaymentEvent
            ObjectMapper objectMapper = new ObjectMapper();
            PaymentEvent payment = objectMapper.readValue(paymentEvent, PaymentEvent.class);

            // Extraire l'email et autres informations du paiement depuis le JSON
            String clientName = payment.getClient().getNom();
            String clientEmail = payment.getClient().getEmail();  // Récupérer l'email du client
            Double montant = payment.getMontant();  // Récupérer le montant du paiement
            String methodPaiement = payment.getMethodPaiement();  // Récupérer le mode de paiement
            String date = payment.getDate();  // Récupérer la date du paiement

            // Vérification des valeurs extraites
            System.out.println("-----------------------");
            System.out.println(" nom du client  " + clientName);
            System.out.println("Notification clientEmail : " + clientEmail);
            System.out.println("Montant : " + montant);
            System.out.println("Mode de paiement : " + methodPaiement);
            System.out.println("Date de paiement : " + date);
            System.out.println("-------------------------");

            // Vérifier si l'email est valide
            if (clientEmail != null && !clientEmail.isEmpty()) {
                Client client = new Client();
                client.setNom(clientName);  // definir nom du client
                client.setEmail(clientEmail);  // Définir l'email du client
                notification.setClient(client);  // Assigner le client à la notification
            } else {
                System.out.println("Email du client non fourni.");
                return;  // Ne pas envoyer si l'email est vide
            }

            // Définir les autres informations dans la notification
            notification.setMontant(montant);  // Définir le montant
            notification.setMethodPaiement(methodPaiement);  // Définir le mode de paiement
            notification.setDate(date);  // Définir la date du paiement

            // Appel du service pour envoyer la notification par email
            notificationService.sendNotification(notification);  // Envoi de la notification
        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion de l'événement de paiement: " + e.getMessage());
        }
    }
//    @KafkaListener(topics = "commande-topic", groupId = "notification-group")
//    public void consumeCommandeEvent(String commandeEvent) {
//        // Logique pour créer une notification à partir de l'événement de commande
//        Notification notification = new Notification();
//        notification.setType("Email");  // Type de notification : Email
//        notification.setContenu("Votre commande a été validée !");
//
//        // Créer un client et ajouter l'email dans l'objet Client
//        Client client = new Client();
//        client.setEmail(notification.getClientEmail());  // Remplacer par l'email réel du client
//        notification.setClient(client);  // Ajouter le client dans la notification
//
//        notificationService.sendNotification(notification);  // Envoi de la notification par email
//    }

}
