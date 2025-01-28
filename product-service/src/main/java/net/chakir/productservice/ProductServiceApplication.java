package net.chakir.productservice;

import net.chakir.productservice.entities.Categorie;
import net.chakir.productservice.entities.Produit;
import net.chakir.productservice.repo.CategorieRepository;
import net.chakir.productservice.repo.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        return args -> {
            // Création de catégories
            Categorie categorie1 = new Categorie(null, "Électronique", "Produits électroniques et gadgets");
            Categorie categorie2 = new Categorie(null, "Maison", "Produits pour la maison et le jardin");

            // Sauvegarde des catégories
            categorieRepository.save(categorie1);
            categorieRepository.save(categorie2);

            // Création de produits
            Produit produit1 = new Produit("Smartphone", "Un smartphone haut de gamme", 999.99, categorie1.getName(), 100);
            Produit produit2 = new Produit("Aspirateur", "Aspirateur puissant et silencieux", 199.99, categorie2.getName(), 50);

            // Sauvegarde des produits
            produitRepository.save(produit1);
            produitRepository.save(produit2);

            System.out.println("Données initiales insérées avec succès !");
        };
    }
}
