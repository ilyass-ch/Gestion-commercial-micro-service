package net.chakir.commandeservice.sevice;

import net.chakir.commandeservice.config.KafkaConfig;
import net.chakir.commandeservice.entities.Commande;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfig kafkaConfig;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    // Publier un événement de paiement (après sérialisation en JSON)
    public void sendCommandeEvent(Commande commande) {
        String serializedCommande = kafkaConfig.serializeObject(commande); // Sérialisation de l'objet Paiement
        kafkaTemplate.send("commande-topic", serializedCommande); // Envoi à Kafka
    }
}
