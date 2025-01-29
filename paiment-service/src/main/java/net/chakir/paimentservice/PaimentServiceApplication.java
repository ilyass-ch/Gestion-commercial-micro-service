package net.chakir.paimentservice;

import net.chakir.paimentservice.entities.Paiement;
import net.chakir.paimentservice.entities.Remboursement;
import net.chakir.paimentservice.repo.PaiementRepository;
import net.chakir.paimentservice.repo.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class PaimentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaimentServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PaiementRepository paiementRepository, RemboursementRepository remboursementRepository) {
        return args -> {
            // Créer des paiements
            Paiement paiement1 = new Paiement(null, 101L, 201L, 1500.0, "Carte Bancaire", "PAYÉ", LocalDate.now(), null, null,null);
            Paiement paiement2 = new Paiement(null, 102L, 202L, 2500.0, "Virement Bancaire", "PAYÉ", LocalDate.now(), null, null, null);
            Paiement paiement3 = new Paiement(null, 103L, 203L, 1800.0, "PayPal", "EN ATTENTE", LocalDate.now(), null, null, null);

            paiementRepository.save(paiement1);
            paiementRepository.save(paiement2);
            paiementRepository.save(paiement3);

            // Créer des remboursements
            Remboursement remboursement1 = new Remboursement(null, paiement1.getId(), paiement1.getClientId(), 500.0, LocalDate.now(), "Erreur de facturation", null);
            Remboursement remboursement2 = new Remboursement(null, paiement2.getId(), paiement2.getClientId(), 800.0, LocalDate.now(), "Produit retourné", null);

            remboursementRepository.save(remboursement1);
            remboursementRepository.save(remboursement2);

            // Afficher les données
            paiementRepository.findAll().forEach(p -> System.out.println("Paiement créé : " + p));
            remboursementRepository.findAll().forEach(r -> System.out.println("Remboursement créé : " + r));
        };
    }

}
