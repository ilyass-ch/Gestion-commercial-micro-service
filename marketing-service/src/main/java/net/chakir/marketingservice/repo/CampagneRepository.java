package net.chakir.marketingservice.repo;

import net.chakir.marketingservice.entities.Campagne;
import net.chakir.marketingservice.enums.CompagneStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CampagneRepository extends JpaRepository<Campagne, Long> {
    Optional<Campagne> findByNom(String nom);
    List<Campagne> findByCompagneStatut(CompagneStatut statut);
    List<Campagne> findByDateDebutBefore(Date date);
    List<Campagne> findByDateFinAfter(Date date);
    List<Campagne> findByDescriptionContaining(String keyword);
}
