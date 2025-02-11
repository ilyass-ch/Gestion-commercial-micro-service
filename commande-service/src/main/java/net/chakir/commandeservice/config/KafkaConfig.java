package net.chakir.commandeservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final String bootstrapServers = "localhost:9092"; // Adresse de votre serveur Kafka

    private final ObjectMapper objectMapper;

    // Injection de l'ObjectMapper configuré
    public KafkaConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Sérialisation en String
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Méthode pour sérialiser un objet en JSON en utilisant l'ObjectMapper configuré
    public String serializeObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object); // Utilisation de l'ObjectMapper configuré
        } catch (Exception e) {
            throw new RuntimeException("Erreur de sérialisation JSON : " + e.getMessage());
        }
    }
}
