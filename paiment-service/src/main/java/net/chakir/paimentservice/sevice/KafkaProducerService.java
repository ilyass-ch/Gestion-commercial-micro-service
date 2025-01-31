package net.chakir.paimentservice.sevice;

import net.chakir.paimentservice.config.KafkaConfig;
import net.chakir.paimentservice.entities.Paiement;
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
    public void sendPaiementEvent(Paiement paiement) {
        String serializedPaiement = kafkaConfig.serializeObject(paiement); // Sérialisation de l'objet Paiement
        kafkaTemplate.send("paiement-topic", serializedPaiement); // Envoi à Kafka
    }
}
