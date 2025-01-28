package net.chakir.paimentservice.transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.chakir.paimentservice.model.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
@FeignClient(name = "COMMANDE-SERVICE")
public interface CommandeRestclient {

    @GetMapping("commandes/{id}")
    @CircuitBreaker(name = "commande-Service", fallbackMethod = "getDefaultCommande")
    Commande getCommandeById(@PathVariable("id") Long id);

    @GetMapping("/commandes")
    List<Commande> getAllCommandes();

    default Commande getDefaultCommande (Long id, Exception e) {
        Commande commande = new Commande();
        commande.setId(id);
        commande.setClientId(0L);
        commande.setDateCreation(LocalDateTime.now());
        commande.setStatut("NOT AVAILABLE");
        commande.setMontantTotal(0);
        return commande;
    }
}
