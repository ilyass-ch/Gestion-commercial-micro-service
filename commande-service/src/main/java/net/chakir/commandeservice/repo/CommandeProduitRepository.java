package net.chakir.commandeservice.repo;

import net.chakir.commandeservice.entities.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeProduitRepository extends JpaRepository<CommandeProduit, Long> {
    List<CommandeProduit> findByCommandeId(Long commandeId);
    List<CommandeProduit> findByProduitId(Long produitId);
}
