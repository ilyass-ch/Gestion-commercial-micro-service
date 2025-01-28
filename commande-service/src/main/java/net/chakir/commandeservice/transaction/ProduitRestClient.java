package net.chakir.commandeservice.transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.chakir.commandeservice.model.Client;
import net.chakir.commandeservice.model.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProduitRestClient {

    @GetMapping("produits/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "getDefaultproduct")
    Produit getProduitById(@PathVariable("id") Long id);

    @GetMapping("/produits")
    List<Produit> getAllProduits ();

    default Produit getDefaultProduit (Long id, Exception e) {
        Produit produit = new Produit();
        produit.setId(id);
        produit.setName("Default Produit");
        produit.setDescription("Default Description");
        produit.setCategorieName("Default Categorie");
        produit.setQuantiteStock(0);
        produit.setPrix(0);
        return produit;
    }
}
