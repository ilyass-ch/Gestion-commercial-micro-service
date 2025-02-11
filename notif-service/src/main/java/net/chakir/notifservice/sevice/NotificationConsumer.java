package net.chakir.notifservice.sevice;

import net.chakir.notifservice.entities.Notification;
import net.chakir.notifservice.model.Client;
import net.chakir.notifservice.model.CommandeEvent;
import net.chakir.notifservice.model.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {

    private final NotificationService notificationService;  // Service pour gérer les notifications
    private final SmsService smsService;  // Service pour gérer l'envoi de SMS

    // Injection des services via le constructeur
    public NotificationConsumer(NotificationService notificationService, SmsService smsService) {
        this.notificationService = notificationService;
        this.smsService = smsService;
    }

    @KafkaListener(topics = "payment-topic", groupId = "notification-group")
    public void consumePaymentEvent(String paymentEvent) {
        try {
            // Utilisation de Jackson pour parser l'événement JSON et récupérer l'objet PaymentEvent
            ObjectMapper objectMapper = new ObjectMapper();
            PaymentEvent payment = objectMapper.readValue(paymentEvent, PaymentEvent.class);

            // Extraire les informations du paiement depuis le JSON
            String clientName = payment.getClient().getNom();
            String clientEmail = payment.getClient().getEmail();  // Récupérer l'email du client
            Double montant = payment.getMontant();  // Récupérer le montant du paiement
            String methodPaiement = payment.getMethodPaiement();  // Récupérer le mode de paiement
            LocalDateTime date = payment.getDate();  // Récupérer la date du paiement

            // Vérification des valeurs extraites
            if (clientEmail == null || clientEmail.isEmpty()) {
                System.out.println("Email du client non fourni.");
                return;  // Ne pas envoyer si l'email est vide
            }

            // Créer un client et ajouter l'email dans l'objet Client
            Client client = new Client();
            client.setNom(clientName);  // définir nom du client
            client.setEmail(clientEmail);  // Définir l'email du client

            // Créer la notification
            Notification notification = new Notification();
            notification.setType("Email");  // Type de notification : Email
            notification.setContenu("Votre paiement de " + montant + " a été accepté.");
            notification.setClient(client);  // Assigner le client à la notification
            notification.setMontant(montant);  // Définir le montant
            notification.setMethodPaiement(methodPaiement);  // Définir le mode de paiement
            notification.setDate(date);  // Définir la date du paiement

            // Enregistrer et envoyer la notification
            notificationService.sendNotification(notification);  // Envoi de la notification

        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion de l'événement de paiement: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "commande-topic", groupId = "notification-group")
    public void consumeCommandeEvent(String commandeEvent) {
        try {
            // Utilisation de Jackson pour parser l'événement JSON et récupérer l'objet CommandeEvent
            ObjectMapper objectMapper = new ObjectMapper();
            CommandeEvent commande = objectMapper.readValue(commandeEvent, CommandeEvent.class);

            // Extraire les informations de la commande depuis le JSON
            String clientName = commande.getClient().getNom();
            String clientPhone = commande.getClient().getTelephone();  // Récupérer le téléphone du client
            String statut = commande.getStatut();  // Récupérer le statut de la commande
            LocalDateTime dateCommande = commande.getDateCreation();  // Récupérer la date de la commande

            // Vérification des valeurs extraites
            if (clientPhone == null || clientPhone.isEmpty()) {
                System.out.println("Numéro de téléphone du client non fourni.");
                return;  // Ne pas envoyer si le téléphone est vide
            }

            // Créer un client et ajouter le numéro de téléphone dans l'objet Client
            Client client = new Client();
            client.setNom(clientName);  // définir nom du client
            client.setTelephone(clientPhone);  // Définir le téléphone du client

            // Créer la notification
            Notification notification = new Notification();
            notification.setType("SMS");  // Type de notification : SMS
            notification.setContenu("Votre commande a été validée. Statut: " + statut);
            notification.setClient(client);  // Assigner le client à la notification
            notification.setStatut(statut);  // Définir le statut de la commande
            notification.setDate(dateCommande);  // Définir la date de la commande



            // Envoi de la notification par SMS
            smsService.sendSms(notification);  // Envoi du SMS via le service SMS

        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion de l'événement de commande: " + e.getMessage());
        }
    }
}
