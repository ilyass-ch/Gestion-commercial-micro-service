package net.chakir.paimentservice.repo;

import net.chakir.paimentservice.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    Paiement findPaiementById(long id);
    List<Paiement> findPaiementByClientId(long id);

}
