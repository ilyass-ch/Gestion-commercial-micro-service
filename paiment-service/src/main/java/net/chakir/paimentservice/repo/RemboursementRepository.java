package net.chakir.paimentservice.repo;
import net.chakir.paimentservice.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long>{

    Remboursement getRemboursementById(Long id);
    List<Remboursement> getRemboursementByPaiementId(Long paiementId);
    List<Remboursement> getRemboursementByClientId(Long clientId);
}
