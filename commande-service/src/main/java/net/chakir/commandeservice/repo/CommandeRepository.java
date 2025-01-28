package net.chakir.commandeservice.repo;

import net.chakir.commandeservice.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByClientId(Long clientId);
    List<Commande> findByStatut(String statut);
    List<Commande> findByDateCreationBetween(LocalDateTime start, LocalDateTime end);
    Optional<Commande> findById (Long id);
}



