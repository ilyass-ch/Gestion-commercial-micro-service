package net.chakir.marketingservice.repo;

import net.chakir.marketingservice.entities.StatistiqueCampagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatistiqueCampagneRepository extends JpaRepository<StatistiqueCampagne, Long> {
    Optional<StatistiqueCampagne> findByCampagneId(Long campagneId);
    List<StatistiqueCampagne> findByNombreEmailsEnvoyesGreaterThan(int nombreEmails);
    List<StatistiqueCampagne> findByTauxOuvertureGreaterThan(double tauxOuverture);
    List<StatistiqueCampagne> findByTauxConversionGreaterThan(double tauxConversion);
}
