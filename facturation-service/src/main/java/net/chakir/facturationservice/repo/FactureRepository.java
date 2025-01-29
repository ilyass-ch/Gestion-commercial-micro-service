package net.chakir.facturationservice.repo;

import net.chakir.facturationservice.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);
    Facture findByCommandeId(Long commandeId);

    List<Facture> id(Long id);
}




