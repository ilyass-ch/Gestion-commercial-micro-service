package net.chakir.commandeservice;

import net.chakir.commandeservice.entities.Commande;
import net.chakir.commandeservice.repo.CommandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableFeignClients
public class CommandeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandeServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CommandeRepository commandeRepository) {
        return args -> {
            // Création de 3 commandes
            Commande commande1 = new Commande(null, Long.valueOf(1), "EN_COURS", 150.75, LocalDateTime.now(), null);
            Commande commande2 = new Commande(null, Long.valueOf(2), "LIVRÉE", 200.00, LocalDateTime.now().minusDays(1), null);
            Commande commande3 = new Commande(null, Long.valueOf(3), "ANNULÉE", 50.00, LocalDateTime.now().minusDays(2), null);

            // Sauvegarde des commandes dans la base de données
            commandeRepository.save(commande1);
            commandeRepository.save(commande2);
            commandeRepository.save(commande3);

            System.out.println("Commandes initialisées avec succès !");
        };
    }
}
