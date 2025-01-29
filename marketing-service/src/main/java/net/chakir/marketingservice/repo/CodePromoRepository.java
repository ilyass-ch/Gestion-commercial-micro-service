package net.chakir.marketingservice.repo;

import net.chakir.marketingservice.entities.CodePromo;
import net.chakir.marketingservice.enums.CodePromoStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CodePromoRepository extends JpaRepository<CodePromo, Long> {
    Optional<CodePromo> findByCode(String code);
    List<CodePromo> findByCodePromoStatut(CodePromoStatut statut);
    List<CodePromo> findByDateExpirationBefore(Date date);
    List<CodePromo> findByMontantReductionGreaterThan(Double amount);
    List<CodePromo> findByDescriptionContaining(String keyword);
}
