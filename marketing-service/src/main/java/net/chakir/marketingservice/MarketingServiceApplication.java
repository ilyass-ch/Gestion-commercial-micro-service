package net.chakir.marketingservice;

import net.chakir.marketingservice.entities.Campagne;
import net.chakir.marketingservice.entities.CodePromo;
import net.chakir.marketingservice.entities.StatistiqueCampagne;
import net.chakir.marketingservice.enums.CodePromoStatut;
import net.chakir.marketingservice.enums.CompagneStatut;
import net.chakir.marketingservice.sevice.CampagneService;
import net.chakir.marketingservice.sevice.CodePromoService;
import net.chakir.marketingservice.sevice.StatistiqueCampagneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MarketingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner initDatabase(CampagneService campagneService, CodePromoService codePromoService, StatistiqueCampagneService statistiqueCampagneService) {
        return args -> {
            // Initialiser une campagne
            Campagne campagne = new Campagne(null, "Promo Été 2024", "Réduction spéciale pour l'été", new Date(), new Date(), CompagneStatut.ACTIF);
            campagneService.saveCompagne(campagne);

            // Initialiser un code promo associé à cette campagne
            CodePromo codePromo = new CodePromo(null, "SUMMER20", "20% de réduction", 20.0, new Date(), CodePromoStatut.ACTIF, campagne);
            codePromoService.saveCodePromo(codePromo);

            // Initialiser une statistique de campagne
            StatistiqueCampagne statistiqueCampagne = new StatistiqueCampagne(campagne.getId(), 5000, 40.5, 10.2);
            statistiqueCampagneService.saveStatistique(statistiqueCampagne);
        };
    }

}
