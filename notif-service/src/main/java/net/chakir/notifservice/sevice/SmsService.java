package net.chakir.notifservice.sevice;


import net.chakir.notifservice.entities.Notification;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;


@Service
    public class SmsService {

        // Injection des informations Twilio depuis application.properties
        @Value("${twilio.accountSid}")
        private String accountSid;

        @Value("${twilio.authToken}")
        private String authToken;

        @Value("${twilio.phoneNumber}")
        private String twilioPhoneNumber;

        // Méthode pour envoyer un SMS
        public void sendSms(Notification notification) {
            try {
                // Initialiser Twilio avec le SID et le token
                Twilio.init(accountSid, authToken);

                // Numéro du client qui recevra le SMS
                String clientPhone = notification.getClient().getTelephone();
                String messageContent = notification.getContenu();

                // Vérifier si le numéro de téléphone du client est valide
                if (clientPhone == null || clientPhone.isEmpty()) {
                    System.out.println("Numéro de téléphone du client manquant.");
                    return;
                }

                // Envoi du SMS via l'API Twilio
                Message message = Message.creator(
                        new PhoneNumber(clientPhone),  // Numéro du destinataire
                        new PhoneNumber(twilioPhoneNumber),  // Numéro Twilio d'envoi
                        messageContent  // Contenu du message
                ).create();

                // Afficher le SID du message envoyé
                System.out.println("SMS envoyé avec succès! SID: " + message.getSid());
            } catch (Exception e) {
                System.out.println("Erreur lors de l'envoi du SMS: " + e.getMessage());
            }
        }
    }

