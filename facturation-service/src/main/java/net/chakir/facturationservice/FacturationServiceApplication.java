package net.chakir.facturationservice;

import net.chakir.facturationservice.entities.Facture;
import net.chakir.facturationservice.entities.Taxe;
import net.chakir.facturationservice.repo.FactureRepository;
import net.chakir.facturationservice.repo.TaxeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class FacturationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(FactureRepository factureRepository, TaxeRepository taxeRepository) {
        return args -> {
            // ✅ Création des taxes
            Taxe tva = new Taxe(null, "TVA", 20.0, "Taxe sur la valeur ajoutée", null);
            Taxe taxeEco = new Taxe(null, "Taxe Écologique", 5.0, "Taxe pour les produits polluants", null);

            taxeRepository.saveAll(Arrays.asList(tva, taxeEco));

            // ✅ Création des factures avec taxes associées
            Facture facture1 = new Facture(null, 1L, 1L, 1000.0, 0.0, 20.0, new Date(), null, null, null);
            Facture facture2 = new Facture(null, 2L, 2L, 2000.0, 0.0, 20.0, new Date(), null, null, null);

            // Ajout des taxes aux factures
            List<Taxe> taxesFacture1 = Arrays.asList(tva, taxeEco);
            List<Taxe> taxesFacture2 = Arrays.asList(tva);

            facture1.setTaxes(taxesFacture1);
            facture2.setTaxes(taxesFacture2);

            // ✅ Calcul du Montant TTC avant sauvegarde
            facture1.setMontantTTC(calculerMontantTTC(facture1));
            facture2.setMontantTTC(calculerMontantTTC(facture2));

            factureRepository.saveAll(Arrays.asList(facture1, facture2));

            // ✅ Affichage des données initialisées
            System.out.println("🚀 Données initialisées avec succès !");
            System.out.println("Taxes disponibles :");
            taxeRepository.findAll().forEach(taxe -> System.out.println("- " + taxe.getName() + " : " + taxe.getPourcentage() + "%"));

            System.out.println("Factures disponibles :");
            factureRepository.findAll().forEach(facture ->
                    System.out.println("- Facture ID " + facture.getId() + " | Montant HT: " + facture.getMontantHT() + " | Montant TTC: " + facture.getMontantTTC()+ " | Client ID "+facture.getClientId())
            );
        };
    }

    // ✅ Méthode pour calculer le montant TTC
    private double calculerMontantTTC(Facture facture) {
        List<Taxe> taxes = facture.getTaxes();
        double tauxTotal = taxes.stream().mapToDouble(Taxe::getPourcentage).sum();
        return facture.getMontantHT() * (1 + (tauxTotal / 100));
    }
}
