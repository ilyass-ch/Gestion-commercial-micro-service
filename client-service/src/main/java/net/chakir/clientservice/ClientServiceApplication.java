package net.chakir.clientservice;

import net.chakir.clientservice.entities.Client;
import net.chakir.clientservice.entities.Sex;
import net.chakir.clientservice.repo.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository) {
        return args -> {
            // Création de 3 clients
            Client client1 = new Client(null, "Chakir", "Ahmed", "ahmed.chakir@example.com",
                    "0612345678", "10 Rue de Paris", "75000", "France", Sex.HOMME);

            Client client2 = new Client(null, "Latifa", "Bensaid", "latifa.bensaid@example.com",
                    "0623456789", "20 Avenue des Champs", "75001", "France", Sex.FEMME);

            Client client3 = new Client(null, "Karim", "El Fassi", "karim.elfassi@example.com",
                    "0634567890", "30 Boulevard Haussmann", "75002", "France", Sex.HOMME);

            // Sauvegarde des clients dans la base de données
            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);

            System.out.println("Clients initialisés avec succès !");
        };
    }

}
