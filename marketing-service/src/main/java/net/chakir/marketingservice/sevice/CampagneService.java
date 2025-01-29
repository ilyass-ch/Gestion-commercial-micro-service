package net.chakir.marketingservice.sevice;

import net.chakir.marketingservice.entities.Campagne;
import net.chakir.marketingservice.enums.CompagneStatut;
import net.chakir.marketingservice.repo.CampagneRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampagneService {
    private final CampagneRepository campagneRepository;

    public CampagneService(CampagneRepository campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    public List<Campagne> getAllCompagnes() {
        return campagneRepository.findAll();
    }

    public Optional<Campagne> getCompagneById(Long id) {
        return campagneRepository.findById(id);
    }

    public Optional<Campagne> getCompagneByNom(String nom) {
        return campagneRepository.findByNom(nom);
    }

    public List<Campagne> getCompagnesByStatut(CompagneStatut statut) {
        return campagneRepository.findByCompagneStatut(statut);
    }

    public List<Campagne> getCompagnesBeforeDate(Date date) {
        return campagneRepository.findByDateDebutBefore(date);
    }

    public List<Campagne> getCompagnesAfterDate(Date date) {
        return campagneRepository.findByDateFinAfter(date);
    }

    public List<Campagne> getCompagnesByDescription(String keyword) {
        return campagneRepository.findByDescriptionContaining(keyword);
    }

    public Campagne saveCompagne(Campagne compagne) {
        return campagneRepository.save(compagne);
    }

    public void deleteCompagne(Long id) {
        campagneRepository.deleteById(id);
    }
}
